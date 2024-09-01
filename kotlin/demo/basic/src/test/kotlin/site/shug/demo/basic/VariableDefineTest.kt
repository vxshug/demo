package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.test.Test


class VariableDefineTest {
    /**
     * val 声明不可变变量, 一旦赋值不可修改
     */
    @Test
    fun valVariable() {
        // val声明不可变
        val lazyInitInt: Int // 可以先声明, 后面再初始化
        lazyInitInt = 1

        // lazyInitInt = 2 // 不可以再赋值

        Assertions.assertEquals(lazyInitInt, 1)

        val valByte: Byte = 1 // 指定类型
        val valShort: Short = 1 // 指定类型
        val valInt = 1 // 推导Int类型
        val valLong = 1L // 声明是Long类型

        Assertions.assertEquals(valByte, 1)
        Assertions.assertEquals(valShort, 1)
        Assertions.assertEquals(valInt, 1)
        Assertions.assertEquals(valLong, 1)

        val valUByte: UByte = 1u // 指定类型
        val valUShort: UShort = 1u // 指定类型
        val valUInt = 1u // 推导UInt类型
        val valULong = 1uL // 声明是ULong类型

        Assertions.assertEquals(valUByte, 1.toUByte())
        Assertions.assertEquals(valUShort, 1.toUShort())
        Assertions.assertEquals(valUInt, 1u)
        Assertions.assertEquals(valULong, 1uL)


        val valFloat = 1.1F // 指定Float类型
        val valDouble = 1.1 // 推导Double类型

        Assertions.assertEquals(valFloat, 1.1F)
        Assertions.assertEquals(valDouble, 1.1)

        val valBoolean = true // 声明Boolean类型
        Assertions.assertTrue(valBoolean)

        val valChar = 'c' // 声明Char类型

        Assertions.assertEquals(valChar, 'c')

        val valString = "Hello world" // 声明String类型
        val templateString = "Hello world ${valByte.toString(16)}" // 模版字符串

        Assertions.assertEquals(valString, "Hello world")
        Assertions.assertEquals(templateString, "Hello world 1")

    }

    /**
     * var 声明可变变量
     */
    @Test
    fun varVariable() {

        var varlazyInitInt: Int // 可以先声明, 后面再初始化
        varlazyInitInt = 1
        varlazyInitInt = 2
        Assertions.assertEquals(varlazyInitInt, 2)

        var varByte: Byte = 1 // 指定类型
        var varShort: Short = 1 // 指定类型
        var varInt = 1 // 推导Int类型
        var varLong = 1L // 声明是Long类型

        Assertions.assertEquals(varByte, 1)
        Assertions.assertEquals(varShort, 1)
        Assertions.assertEquals(varInt, 1)
        Assertions.assertEquals(varLong, 1)

        var varUByte: UByte = 1u // 指定类型
        var varUShort: UShort = 1u // 指定类型
        var varUInt = 1u // 推导UInt类型
        var varULong = 1uL // 声明是ULong类型

        Assertions.assertEquals(varUByte, 1.toUByte())
        Assertions.assertEquals(varUShort, 1.toUShort())
        Assertions.assertEquals(varUInt, 1u)
        Assertions.assertEquals(varULong, 1uL)


        var varFloat = 1.1F // 指定Float类型
        var varDouble = 1.1 // 推导Double类型

        Assertions.assertEquals(varFloat, 1.1F)
        Assertions.assertEquals(varDouble, 1.1)

        var varBoolean = true // 声明Boolean类型
        Assertions.assertTrue(varBoolean)

        var varChar = 'c' // 声明Char类型

        Assertions.assertEquals(varChar, 'c')

        var varString = "Hello world" // 声明String类型
        var varTemplateString = "Hello world ${varByte.toString(16)}" // 模版字符串

        Assertions.assertEquals(varString, "Hello world")
        Assertions.assertEquals(varTemplateString, "Hello world 1")
    }
}