package site.shug.demo.basic.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * Flow异步计算的流
 * Flow是冷流, 只有调用终止操作符才会开始运行
 */
class FlowTest {
    @Test
    fun testFlow() = runBlocking {
        val channel = Channel<Int>(UNLIMITED)
        // flow创建Flow
        val f = flow {
            for (i in 1..3) {
                channel.send(i)
                delay(100L)
                // 将值加入到流中
                emit(i)
            }
        }
        launch {
            for (i in 1..3) {
                channel.send(i)
                delay(100L)
            }
        }
        /**
         * 终结操作符
         * - 转化成集合, ToList等
         * - first()获取第一个值
         * - reduce, fold
         * - collect
         */
        f.collect { println(it) }
        delay(1000L)
        channel.close()
        Assertions.assertEquals(listOf(1, 1, 2, 2, 3, 3), channel.toList())
    }

    /**
     * flow构建器中的代码必须遵守上下文保留属性
     */
    @Test
    fun testFlowContext() = runBlocking {
        val flow = flow {
            // 将报错
            withContext(Dispatchers.Default) {
                emit(1)
            }
        }

        // 指定flow产生值的上下文
        val flow1 = flow {
            emit(1)
        }.flowOn(Dispatchers.Default)
    }

    /**
     * 丢弃处理速度更不上的值
     */
    @Test
    fun testConflate() = runBlocking {
        val flow = flow {
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }
        val list = flow
            .conflate() // 丢弃处理不了的数据
            .map {
                delay(300)
                it
            }
            .toList()
        Assertions.assertEquals(listOf(1, 3), list)
    }

    /**
     * 缓存值
     */
    @Test
    fun testBuffer() = runBlocking {
        val flow = flow {
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }
        val list = flow
            .buffer() // 缓存
            .toList()
        Assertions.assertEquals(listOf(1, 2, 3), list)
    }

    /**
     * xxxLatest只用于最新值, 会取下先前值的运行
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLatest() = runBlocking {
        val flow = flow {
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }
        val list = flow
            .mapLatest {
                delay(300)
                it
            } // 只处理最新的值
            .toList()
        Assertions.assertEquals(listOf(3), list)
    }

    /**
     * 组合两个流, 组合流的长度是两个流中最短的流长度
     * 会等两流同时有数据
     */
    @Test
    fun testZip() = runBlocking {
        val intFlow = listOf(1,2,3).asFlow().onEach { delay(300) }
        val strFlow = listOf("one", "two", "three", "four").asFlow().onEach { delay(400) }
        val list = strFlow.zip(intFlow) { a, b -> a + b }
            .toList()
        Assertions.assertEquals(listOf("one1", "two2", "three3").toList(), list)
    }

    /**
     * 组合两个流, 每一个流有数据都会产生数据
     */
    @Test
    fun testCombine() = runBlocking {
        val intFlow = listOf(1,2,3).asFlow().onEach { delay(300) }
        val strFlow = listOf("one", "two", "three", "four").asFlow().onEach { delay(400) }
        val list = strFlow.combine(intFlow) { a, b -> a + b }
            .toList()
        Assertions.assertEquals(listOf("one1", "one2", "two2", "two3", "three3", "four3").toList(), list)
    }

    /**
     * 将流展平
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFlat() = runBlocking {
        val flatMapConcat = listOf(1,2,3)
            .asFlow()
            .flatMapConcat { //  按顺序等待内部流完成，然后再开始收集下一个流，
                flow {
                    for (i in 1..it) {
                        emit(it)
                    }
                }
            }
            .toList()
        Assertions.assertEquals(listOf(1, 2, 2, 3, 3, 3), flatMapConcat)

        val flatMapMerge = listOf(1, 2, 3)
            .asFlow()
            .flatMapMerge { // 并发获取流中的数据
                flow { emit(it) }
            }
            .toList()

        Assertions.assertEquals(listOf(1, 2, 3), flatMapMerge)
    }

    /**
     * 捕获异常
     */
    @Test
    fun testCatch() = runBlocking {
        val cancelFlow = listOf(1,2,3)
            .asFlow()
            .map {
                if (it == 2) {
                    //cancel() // 抛出JobCancellationException
                }
                it
            }
            .toList()
        Assertions.assertEquals(listOf(1, 2, 3), cancelFlow)

        val catchFlow = listOf(1,2,3)
            .asFlow()
            .map {
                if (it == 2) {
                    throw RuntimeException("this error")
                }
                it
            }.catch { // 捕获到异常, 流完成, 不再产生值
                println(it.message)
            }
            .toList()
        Assertions.assertEquals(listOf(1), catchFlow)
    }
}