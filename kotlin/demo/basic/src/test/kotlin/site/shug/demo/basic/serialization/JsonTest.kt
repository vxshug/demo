package site.shug.demo.basic.serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

/**
 * Json序列化
 * 需要插件 kotlin("plugin.serialization") version "2.0.20"
 * 需要依赖库 implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
 */
class JsonTest {

    /**
     * 序列化测试
     */
    @Test
    fun testSerialize() {
        val person = Person("shug", 18)
        val str = Json.encodeToString(person)
        Assertions.assertEquals(str, """{"name":"shug","age":18}""")
    }

    /**
     * 反序列化测试
     */
    @Test
    fun testDeserialize() {
        val person = Person("shug", 18)
        val str = """{"name":"shug","age":18}"""
        val person1 = Json.decodeFromString<Person>(str)
        Assertions.assertEquals(person1, person)
    }

    /**
     * JsonElement可以接收所有Json类型
     */
    @Test
    fun testJsonElement() {
        val elementStr = Json.encodeToString(Person("shug", 18))
        val element = Json.decodeFromString<JsonElement>(elementStr)
        Assertions.assertEquals(element.jsonObject.get("name")?.jsonPrimitive?.content, "shug")
    }
}

// 使用Serializable标记开启序列化支持
@Serializable
data class Person(val name: String, val age: Int)