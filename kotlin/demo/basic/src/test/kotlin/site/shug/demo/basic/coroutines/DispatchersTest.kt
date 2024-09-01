package site.shug.demo.basic.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

/**
 * CoroutineDispatcher是CoroutineDispatcher(CoroutineContext的子类)的实现类的分组
 * 协程调度器(Dispatchers)确定相应的协程使用哪个或哪些线程来执行。
 * 协程调度器可以将协程执行限制到特定线程，将其调度到线程池，或让它不受限制地运行。
 * Dispatchers.Default 默认开启的线程池
 * Dispatchers.Main 在安卓上是UI主线程
 * Dispatchers.Unconfined 不局限于任何特定线程的协程调度程序。
 * 它在当前调用帧中执行协程的初始延续，并让协程在相应挂起函数使用的任何线程中恢复，而无需强制执行任何特定的线程策略。
 * Dispatchers.IO 将阻塞 IO 任务卸载到共享线程池。该池中的其他线程是根据需要创建的。默认IO池大小为64
 */
class DispatchersTest {
    @Test
    fun testDefault() = runBlocking { // 运行在当前线程
        // 线程名称类似 Test worker @coroutine#1
        println(Thread.currentThread().name)

        launch(Dispatchers.Default) {
            // 线程名称类似 DefaultDispatcher-worker-1 @coroutine#2
            println(Thread.currentThread().name)
        }
        Unit
    }

    @Test
    fun testUnconfined() = runBlocking {
        println(Thread.currentThread().name)
        launch(Dispatchers.Unconfined) {
            println(Thread.currentThread().name)
        }
        Unit
    }

    @Test
    fun testIO() = runBlocking {
        println(Thread.currentThread().name)
        launch(Dispatchers.IO) {
            println(Thread.currentThread().name)
        }
        Unit
    }
}