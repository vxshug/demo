package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * 函数测试
 */
class FunctionsTest {
    @Test
    fun testNormal() {
        Assertions.assertEquals(addOne(2), 3)
    }

    /**
     * 命名参数, 与函数参数的传入顺序无关
     */
    @Test
    fun testNameFunction() {
        Assertions.assertEquals(nameFunction(drop = 2, ret = 10), 10)
    }

    /**
     * 给函数参数添加默认值
     */
    @Test
    fun testDefaultFunction() {
        Assertions.assertEquals(defaultFunction(), 2)
    }

    /**
     * 单行函数
     */
    @Test
    fun testSingle() {
        Assertions.assertEquals(add(1, 2), 3)
    }

    /**
     * Lambda函数
     */
    @Test
    fun testLambda() {
        val lambda = { x: Int, y: Int -> x + y }
        Assertions.assertEquals(lambda(1, 2), 3)
    }

    /**
     * 泛形函数
     */
    @Test
    fun testGeneric() {
        Assertions.assertEquals(genericFn(1), 1)
        Assertions.assertEquals(genericFn(1.1F), 1.1F)
    }

    /**
     * 使用infix的函数, 可以简化调用方式
     */
    @Test
    fun testInfixFunction() {
       val pair = 1 pair "one"
       Assertions.assertEquals(pair.first, 1)
       Assertions.assertEquals(pair.second, "one")
    }
}

fun addOne(int: Int): Int {
    return int + 1
}

fun nameFunction(ret: Int, drop: Int): Int {
    return ret
}

fun defaultFunction(p: Int = 2): Int {
    return p
}

fun add(a: Int, b: Int) = a + b

fun <T> genericFn(t: T) = t

infix fun <A, B> A.pair(that: B): Pair<A, B> = Pair(this, that)