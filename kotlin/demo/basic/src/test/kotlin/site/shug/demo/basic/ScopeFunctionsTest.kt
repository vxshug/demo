package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * Kotlin 标准库包含多个函数，其唯一目的是在对象上下文中执行代码块。
 * 在提供了lambda 表达式的对象上调用此类函数时，它会形成一个临时作用域。
 * 在此范围内，可以访问对象而不需要其名称。
 * 此类函数称为作用域函数。其中有五个： let 、 run 、 with 、 apply和also 。
 */
class ScopeFunctionsTest {

    /**
     * fun <T, R> T.let(block: (T) -> R): R
     * let会将let的调用者传入block中
     * let会将block中的返回值返回
     */
    @Test
    fun testLet() {
        val age = 18
        val ret = age.let {
            Assertions.assertEquals(age, it) // age 和it是一样的
            1 // lambda的返回值
        }
        Assertions.assertEquals(ret, 1)
    }

    /**
     * fun <T, R> T.run(block: T.() -> R): R
     * run将block声明weiT的拓展函数
     * 在block中可以用this访问调用者
     * run会将block的返回值返回
     */
    @Test
    fun testRun() {
        val age = 18
        val ret = age.run {
            Assertions.assertEquals(age, this)
            1
        }
        Assertions.assertEquals(ret, 1)
    }

    /**
     * fun <T, R> with(receiver: T, block: T.() -> R): R
     * with和run类似, 但是with不是拓展函数, 上下文对象必须作为参数传递
     */
    @Test
    fun testWith() {
        val age = 18
        val ret = with(age) {
            Assertions.assertEquals(age, this)
            1
        }
        Assertions.assertEquals(ret, 1)
    }

    /**
     * fun <T> T.apply(block: T.() -> Unit): T
     * apply中block是调用者的拓展函数, 在block中可以通过this访问调用者
     * 返回值是调用者
     */
    @Test
    fun testApply() {
        val age = 18
        val ret = age.apply {
            Assertions.assertEquals(age, this)
        }
        Assertions.assertEquals(ret, age)
    }

    /**
     * fun <T> T.also(block: (T) -> Unit): T
     * 与apply相似, 但是上下文对象通过参数传递
     */
    @Test
    fun testAlso() {
        val age = 18
        val ret = age.also {
            Assertions.assertEquals(age, it)
        }
        Assertions.assertEquals(ret, age)
    }
}