package site.shug.demo.basic.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * CoroutineContext上下文对象
 * 当一个协程在另一个协程的CoroutineScope中启动时，它会通过CoroutineScope.coroutineContext继承其上下文，
 * 并且新协程的Job成为父协程 Job 的子级。
 * 当父协程被取消时，它的所有子协程也会被递归取消。
 *
 * 覆盖父CoroutineContext
 * 1. 当启动协程时显式指定不同的作用域
 * 2. 当另一个Job对象作为新协程的上下文传递时
 */
class CoroutineContextTest {

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun testOver() = runBlocking {
        val channel = Channel<String>(UNLIMITED)

        val job = launch {
            // 在新的作用域
            GlobalScope.launch {
                delay(80L)
                channel.send("IO")
            }

            delay(100L)
            channel.send("cancel")
        }
        delay(10L)
        job.cancel()
        delay(1000L)
        channel.close()
        Assertions.assertEquals(listOf("IO"), channel.toList())
    }

    @Test
    fun testOverJob() = runBlocking {
        val channel = Channel<String>(UNLIMITED)

        val job = launch {
            // 另一个Job对象作为新协程的上下文
            launch(Job()) {
                delay(80L)
                channel.send("Job")
            }

            delay(100L)
            channel.send("cancel")
        }
        delay(10L)
        job.cancel()
        delay(1000L)
        channel.close()
        Assertions.assertEquals(listOf("Job"), channel.toList())
    }

    /**
     * CoroutineContext可以用+合并
     */
    @Test
    fun testContextAdd() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        // CoroutineName 设置协程名称
        launch(Dispatchers.Default + CoroutineName("my coroutine")) {
            val name = coroutineContext[CoroutineName] // 获取协程名称
            channel.send(name?.name ?: "null")
            channel.close()
        }
        Assertions.assertEquals(listOf("my coroutine"), channel.toList())
    }
}
