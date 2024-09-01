package site.shug.demo.basic.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * Channel()的参数
 * capacity: 通道的容量, 默认是RENDEZVOUS,
 * RENDEZVOUS: 容量为0, 表示没有缓存, 发送数据将阻塞
 * CONFLATED: 在 Channel() 工厂函数中请求合并通道。这是使用 onBufferOverflow = DROP_OLDEST 创建通道的快捷方式。
 * UNLIMITED: 在 Channel() 工厂函数中请求具有无限容量缓冲区的通道。
 * BUFFERED: 在 Channel() 工厂函数中请求具有默认缓冲区容量的缓冲通道。溢出时暂停的通道的默认容量为 64，可以通过在 JVM 上设置 DEFAULT_BUFFER_PROPERTY_NAME 来覆盖。对于非暂停通道，使用容量为 1 的缓冲区。
 * 自定义: 指定Buffer的容量
 *
 * onBufferOverflow: 默认值BufferOverflow.SUSPEND
 * BufferOverflow.SUSPEND: 缓冲区溢出时暂停。
 * BufferOverflow.DROP_OLDEST: 溢出时删除缓冲区中最旧的值，将新值添加到缓冲区，不要暂停。
 * BufferOverflow.DROP_LATEST: 溢出时保持缓冲区不变，删除要添加的值，不要暂停。
 */
class ChannelTest {

    fun testChannel() = runBlocking {
        val channel = Channel<Int>()
    }
    /**
     * 消费-生产者模式
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testProduce() = runBlocking {
        val produce = produce<Int> {
            send(1)
            delay(100)
            send(1)
        }
        Assertions.assertEquals(listOf(1, 1), produce.toList())
    }
}