package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.test.Test

/**
 * 反射
 */

class ReflectTarget(val name: String) {
    constructor(name: String, age: Int) : this(name)

    var age = 18

    fun prefixName(): String {
        return "Prefix: $name"
    }
}

class ReflectTest {
    /**
     * 获取类名
     */
    @Test
    fun testClassName() {
        Assertions.assertEquals("ReflectTarget", ReflectTarget::class.simpleName)
    }

    @Test
    fun testConstructor() {
        val kClass = ReflectTarget::class
        Assertions.assertEquals(2, kClass.constructors.size) // 两个构造函数
        val target = kClass.primaryConstructor?.call("primaryConstructor") // 使用主构造函数创建对象
        Assertions.assertEquals("primaryConstructor", target?.name)

        val second = kClass.constructors.toList().get(1).call("second") // 调用其他构造函数
        Assertions.assertEquals("second", second.name)
    }

    @Test
    fun testProperty() {
        val kClass = ReflectTarget::class
        val property = kClass.memberProperties.find { it.name == "name" } // name属性
        val target = ReflectTarget("ReflectTarget")
        val targetName = property?.getter?.call(target) // 获取属性
        Assertions.assertEquals("ReflectTarget", targetName)
        val ageProperty = kClass.memberProperties.find { it.name == "age"  } // age属性
        Assertions.assertEquals(18, target.age)
        (ageProperty as? KMutableProperty1<ReflectTarget, Int>)?.set(target, 35)
        Assertions.assertEquals(35, target.age)
    }

    @Test
    fun testMethod() {
        val kClass = ReflectTarget::class
        val method = kClass.memberFunctions.find { it.name == "prefixName" }
        val target = ReflectTarget("ReflectTarget")
        val targetName = method?.call(target)
        Assertions.assertEquals("Prefix: ReflectTarget", targetName)
    }
}