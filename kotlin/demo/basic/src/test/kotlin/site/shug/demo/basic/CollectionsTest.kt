package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * kotlin的Collections类型
 */
class CollectionsTest {

    /**
     * List是不变的有序数组
     */
    @Test
    fun testList() {
        val list = listOf(1, 2, 3)
        Assertions.assertEquals(list.size, 3)
        Assertions.assertEquals(list, intArrayOf(1,2,3).toList())
    }
    /**
     * MutableList是可变的有序数组
     */
    @Test
    fun testMutableList() {
        val list = mutableListOf(1, 2, 3)
        list.add(4)
        Assertions.assertEquals(list.size, 4)
        Assertions.assertEquals(list, intArrayOf(1,2,3,4).toList())
    }

    /**
     * Set是不可变的无序唯一集合
     */
    @Test
    fun testSet() {
        val set = setOf(1, 2, 3)
        Assertions.assertEquals(set.size, 3)
        Assertions.assertEquals(set, intArrayOf(1,2,3).toSet())
    }

    /**
     * MutableSet是可变的无序唯一集合
     */
    @Test
    fun testMutableSet() {
        val set = mutableSetOf(1, 2, 3)
        set.add(4)
        Assertions.assertEquals(set.size, 4)
        Assertions.assertEquals(set, intArrayOf(1,2,3,4).toSet())
    }

    /**
     * Map是不可变的键值对
     */
    @Test
    fun testMap() {
        val map = mapOf("one" to 1, "two" to 2)
        Assertions.assertEquals(map.size, 2)
        Assertions.assertEquals(map["one"], 1)
        Assertions.assertEquals(map["two"], 2)
        Assertions.assertNull(map["there"])
    }

    /**
     * MutableMap是可变的键值对
     */
    @Test
    fun testMutableMap() {
        val map = mutableMapOf("one" to 1, "two" to 2)
        map["there"] = 3
        Assertions.assertEquals(map.size, 3)
        Assertions.assertEquals(map["one"], 1)
        Assertions.assertEquals(map["two"], 2)
        Assertions.assertEquals(map["there"], 3)
    }
}