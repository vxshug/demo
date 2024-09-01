package site.shug.demo.basic.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.test.Test

/**
 * 协程中的Exception
 * 协程构建器有两种风格：自动传播异常（ launch ）或将异常暴露给用户（ async和Produce ）。
 * 当这些构建器用于创建根协程（不是另一个协程的子级）时，
 * 前面的构建器将异常视为未捕获的异常，类似于 Java Thread.uncaughtExceptionHandler ，
 * 而后者依赖用户消耗最终的异常，例如通过等待或接收（生产和接收在通道部分中介绍）。
 *
 * 所有子协程（在另一个Job的上下文中创建的协程）将其异常的处理委托给其父协程，
 * 父协程也委托给父协程，依此类推，直到根，因此永远不会使用安装在其上下文中的CoroutineExceptionHandler 。
 * 除此之外，async构建器总是捕获所有异常并将它们表示在生成的Deferred对象中，因此它的CoroutineExceptionHandler也不起作用。
 *
 */
class ExceptionTest {
    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun testException() = runBlocking {
        try {
            val job = GlobalScope.launch {
                println("来自launch的异常")
                throw IndexOutOfBoundsException() // 由Thread.defaultUncaughtExceptionHandler处理
            }
            job.join()
        } catch (e: Throwable) {
            println("捕获不到异常") // 捕获不到异常
        }

        val deferred = GlobalScope.async {
            println("来自async的异常")
            throw ArithmeticException()
        }
        try {
            deferred.await()
            println("不会执行")
        } catch (e: ArithmeticException) {
            println("捕获到async的异常")
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun testCatchException() = runBlocking {
        val handler = CoroutineExceptionHandler { context, exception ->
            println("捕获launch的异常: $exception")
        }
        try {
            val job = GlobalScope.launch(handler) {
                println("来自launch的异常")
                throw IndexOutOfBoundsException() // 由CoroutineExceptionHandler处理
            }
            job.join()
        } catch (e: Throwable) {
            println("捕获不到异常") // 捕获不到异常
        }
    }

    /**
     * 如果协程遇到CancellationException以外的异常，它会取消带有该异常的父级。
     */
    @Test
    fun testCancel() = runBlocking {
        launch {
            println("Parent")
            launch {
                println("Child")
                delay(100)
                throw ArithmeticException()
            }
            delay(150)
            println("Parent 2") // 不会打印
        }
        println("Done")
    }

    /**
     * child1异常, 会导致child2及其父Job退出
     */
    @Test
    fun testNoSupervision() = runBlocking {
        val child1 = launch {
            delay(100)
            println("Child")
            throw ArithmeticException()
        }
        val child2 = launch {
            println("Child 2 enter")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child 2 cancel")
            }
        }
        child1.join()
        delay(200L)
        println("parent cancel")
        child2.join()
    }
    /**
     * SupervisorJob在一个父协程中管理多个子协程，并确保即使一个子协程失败，其他子协程也不会受到影响。
     */
    @Test
    fun testSupervision() = runBlocking {
        val supervisor = SupervisorJob()
        val scope = CoroutineScope(coroutineContext + supervisor)
        println("Parent")
        val child1 = scope.launch {
            delay(100)
            println("Child")
            throw ArithmeticException()
        }
        val child2 = scope.launch {
            println("Child 2 enter")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child 2 cancel")
            }
        }
        child1.join()
        delay(200L)
        println("supervisor cancel")
        supervisor.cancel()
        child2.join()
    }
}