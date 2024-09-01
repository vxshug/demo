plugins {
    kotlin("jvm")
    // 序列化插件
    kotlin("plugin.serialization") version "2.0.20"
}

group = "site.shug.demo.basic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // json序列化库
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC.2")

    // 反射依赖
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}