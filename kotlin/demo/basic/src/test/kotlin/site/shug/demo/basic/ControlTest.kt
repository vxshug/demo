package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * 控制流
 */
class ControlTest {
    /**
     * if分支, 优先使用when
     */
    @Test
    fun testIf() {
        val age = 18
        val ret = if (age < 18) {
            1
        } else {
            2
        }
        Assertions.assertEquals(ret, 2)
        val result = if (age < 18) 1 else 0
        Assertions.assertEquals(result, 0)
    }

    /**
     * when分支
     */
    @Test
    fun testWhen() {
        val age = 18
        val ret = when (age) {
            18 -> 1
            else -> 2
        }
        Assertions.assertEquals(ret, 1)

        val result = when {
            age < 18 -> 1
            else -> 0
        }
        Assertions.assertEquals(result, 0)
    }

    /**
     * 创建范围, 常用与循环
     */
    @Test
    fun testRange() {
        val range0 = 0..4
        Assertions.assertEquals(range0.toList(), listOf(0, 1, 2, 3, 4))

        val range1 = 0..<4
        Assertions.assertEquals(range1.toList(), listOf(0, 1, 2, 3))

        val range2 = 4 downTo  0
        Assertions.assertEquals(range2.toList(), listOf(4, 3, 2, 1, 0))

        val range3 = 0..4 step 2
        Assertions.assertEquals(range3.toList(), listOf(0, 2, 4))

        val range4 = 'a'..'d'
        Assertions.assertEquals(range4.toList(), listOf('a', 'b', 'c', 'd'))

        val range5 = 'a'..'d' step 2
        Assertions.assertEquals(range5.toList(), listOf('a', 'c'))
    }

    /**
     * 循环语句, 使用的Iterable接口
     */
    @Test
    fun testFor() {
        val list = mutableListOf<Int>()
        for (i in 1..10) {
            list.add(i)
        }
        Assertions.assertEquals(list.size, 10)
        Assertions.assertEquals(list, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

        val list1 = mutableListOf<Int>()
        for (i in 1..10) {
            if (i % 2 == 0) {
                // 跳出当前循环, 进入下一个循环
                continue
            }
            list1.add(i)
        }
        Assertions.assertEquals(list1.size, 5)
        Assertions.assertEquals(list1, listOf(1, 3, 5, 7, 9))

        val list2 = mutableListOf<Int>()
        for (i in 1..10) {
            if (i == 3) {
                // 结束整个循环
                break
            }
            list2.add(i)
        }
        Assertions.assertEquals(list2.size, 2)
        Assertions.assertEquals(list2, listOf(1, 2))

        val list3 = mutableListOf<Int>()
        outfor@ for (i in 1..10) {
            for (j in 1..2) {
                if (i == 2) {
                    // 继续外部outfor标签的循环
                    continue@outfor
                }
                list3.add(i)
            }
        }
        Assertions.assertEquals(list3.size, 18)
        Assertions.assertEquals(list3, listOf(1, 1, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10))

        val list4 = mutableListOf<Int>()
        outfor@ for (i in 1..10) {
            for (j in 1..2) {
                if (i == 2) {
                    // 跳出外部outfor标签的循环
                    break@outfor
                }
                list4.add(i)
            }
        }
        Assertions.assertEquals(list4.size, 2)
        Assertions.assertEquals(list4, listOf(1, 1))
    }

    /**
     * while循环
     */
    @Test
    fun testWhile() {
        val list = mutableListOf<Int>()
        while (list.size <= 10) {
            list.add(list.size)
        }
        Assertions.assertEquals(list.size, 11)
        Assertions.assertEquals(list, listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

        // 先执行一遍do里面的代码, 再判断while的条件
        val list1 = mutableListOf<Int>()
        do {
            list1.add(list1.size)
        } while (list1.size <= 0)
        Assertions.assertEquals(list1.size, 1)
        Assertions.assertEquals(list1, listOf(0))
    }
}