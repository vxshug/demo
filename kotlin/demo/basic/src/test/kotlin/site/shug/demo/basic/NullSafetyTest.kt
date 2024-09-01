package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * 默认情况下，类型不允许接受null值。可空类型是通过显式添加?来声明的。在类型声明之后。
 */
class NullSafetyTest {
    @Test
    fun testCanNull() {
        val nullString: String? = null
        Assertions.assertNull(nullString)
    }

    @Test
    fun testSafeCall() {
        val nullString: String? = null
        val isNull = nullString?.isEmpty() // ?.安全调用, 如果nullString是null, 直接返回null
        Assertions.assertNull(isNull)
    }

    @Test
    fun testElvis() {
        val nullString: String? = null
        val name = nullString ?: "default" // 如果nullString是null, 使用默认值
        Assertions.assertEquals(name, "default")
    }
}