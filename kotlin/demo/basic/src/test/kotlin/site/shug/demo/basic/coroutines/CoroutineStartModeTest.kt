package site.shug.demo.basic.coroutines

import kotlinx.coroutines.CoroutineStart.DEFAULT
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * CoroutineStart是协程启动模式
 *  - [DEFAULT] 立即根据上下文安排协程执行。
 *  - [LAZY] 延迟初始调度的时刻，直到需要协程的结果为止。
 *  - [ATOMIC] 防止协程在启动前被取消，确保其代码能够启动。
 *  - [UNDISPATCHED] 立即执行协程直到其在当前线程中的第一个暂停点。
 */
class CoroutineStartModeTest {
    @Test
    fun testDefault() = runBlocking {
        val channel = Channel<String>(UNLIMITED)
        launch(start = DEFAULT) {
            channel.send("DEFAULT")
            channel.close()
        }
        Assertions.assertEquals(listOf("DEFAULT"), channel.toList())
    }

    @Test
    fun testLazy() = runBlocking {
        val channel0 = Channel<String>(UNLIMITED)
        val job0 = launch(start = LAZY) {
            channel0.send("LAZY")
        }
        channel0.close()
        Assertions.assertEquals(emptyList<String>(), channel0.toList())
        job0.cancel() // 取消协程, 不然runBlocking不会退出

        val channel1 = Channel<String>(UNLIMITED)
        val job1 = launch(start = LAZY) {
            channel1.send("LAZY")
            channel1.close()
        }
        job1.start() // 开始运行协程
        Assertions.assertEquals(listOf("LAZY"), channel1.toList())
    }
}