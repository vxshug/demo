package site.shug.demo.basic.coroutines

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.jupiter.api.Assertions
import java.time.Duration
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.Test

/**
 * 协程是可挂起计算的实例。
 * 它在概念上类似于线程，因为它需要与其余代码同时运行的代码块。
 * 但是，协程不绑定到任何特定线程。它可以在一个线程中挂起其执行并在另一线程中恢复。
 *
 * kotlin协程遵循结构化并发原则, 这意味着新的协程只能在特定的协程作用域中启动，该作用域限定了协程的生命周期.
 */
class FirstTest {

    /**
     * public actual fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T
     * actual 表示这是runBlocking是多平台支持的函数的当前平台实现
     * context 默认值是当前线程上的事件循环。
     * block 协程代码块
     */
    @Test
    fun testRun() = runBlocking { // 开启协程, 并阻塞当前线程, this指向CoroutineScope
        val channel = Channel<String>(UNLIMITED) // 创建无限容量的有缓存通道
        launch { // CoroutineScope的拓展函数, 启动一个新的协程,
            delay(1000L) // 非阻塞延迟1s
            channel.send("World!")
            channel.close()
        }

        channel.send("Hello") // 不延迟
        Assertions.assertEquals(listOf("Hello", "World!"), channel.toList())
    }

    /**
     * coroutineScope创建一个协程范围, 等待其主体及其所有子级完成。
     */
    @Test
    fun testCoroutineScope() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        coroutineScope { // 会等待块里面的的代码. 和创建的协程都完成后才会返回
            delay(1000L)
            launch {
                channel.send("coroutineScope 1")
            }
            channel.send("coroutineScope 2")
        }
        channel.send("main")
        channel.close()
        Assertions.assertEquals(listOf("coroutineScope 2", "coroutineScope 1", "main"), channel.toList())
    }

    /**
     * 测试launch启动协程
     */
    @Test
    fun testLaunch() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        val job = launch { // launch返回一个Job对象，该对象是启动的协程的句柄，可用于显式取消或等待其完成
            delay(100L)
            channel.send("coroutineScope 1")
        }
        job.cancel() // 取消协程
        channel.close()
        Assertions.assertEquals(emptyList<String>(), channel.toList())
    }

    /**
     * async也可以创建协程, 返回的Deferred是Job的子类, 增加了获取返回值的功能
     */
    @Test
    fun testAsync() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        val deferred = async {
            delay(100L)
            "Return"
        }
        channel.send(deferred.await())
        channel.close()
        Assertions.assertEquals(listOf("Return"), channel.toList())
    }

    /**
     * 协程取消是合作性的。协程代码必须配合才能取消。
     * kotlinx.coroutines中的所有挂起函数都是可以取消的。
     * 需要代码检测是否取消, 并且在取消的时候抛出CancellationException
     */
    @Test
    fun testCancellation() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        val job = launch {
            // 如何要实现推出, 检测isActive, 在isActive=false时抛出CancellationException
            try {
                delay(100L)
            } catch (e: CancellationException) {
                // 捕获CancellationException, 使代码不可取消
            }
            channel.send("CancellationException")
        }
        delay(10L) // 添加小延迟, 使job的代码块运行到delay
        job.cancel()
        delay(500L)
        channel.close()
        Assertions.assertEquals(listOf("CancellationException"), channel.toList())
    }

    /**
     * 添加在协程取消后运行的代码
     */
    @Test
    fun tesFinally() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        val job = launch {
            try {
                delay(1000L)
            } finally { // 不管是否取消都会执行
                // delay(10L) // 会再次抛出CancellationException, 不会执行后面代码
                channel.send("Finally") // send挂起函数, 居然没有受到CancellationException的影响
                channel.close()
            }
        }
        delay(100L)
        job.cancel()
        Assertions.assertEquals(listOf("Finally"), channel.toList())
    }

    @Test
    fun testWithContext() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        val job = launch {
            try {
                delay(100L)
            } finally { // 如果finally中需要调用挂起函数, 使用withContext(NonCancellable), 忽略取消
                withContext(NonCancellable) {
                    delay(100L)
                }
                channel.send("Finally")
                channel.close()
            }
        }
        delay(10L)
        job.cancel()
        Assertions.assertEquals(listOf("Finally"), channel.toList())
    }

    /**
     * 超时会抛出TimeoutCancellationException
     */
    @Test
    fun testTimeout() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        launch {
            try {
                withTimeout(Duration.ofSeconds(1)) {
                    delay(10000L)
                }
            } catch (e: TimeoutCancellationException) {
                channel.send("TimeoutCancellationException")
                channel.close()
            }
        }
        delay(2000L)
        Assertions.assertEquals(listOf("TimeoutCancellationException"), channel.toList())
    }

    /**
     * 超时会返回null
     */
    @Test
    fun testTimeoutOrNull() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        launch {
            val ret = withTimeoutOrNull(100L) {
                delay(10000L)
            }
            channel.send(ret.toString())
        }
        delay(2000L)
        channel.close()
        Assertions.assertEquals(listOf("null"), channel.toList())
    }
}

/**
 * 拓展Channel, 将结果转换成List
 */
suspend fun <T> Channel<T>.toList(): List<T> {
    val list = mutableListOf<T>()
    for (item in this) {
        list.add(item)
    }
    return list
}