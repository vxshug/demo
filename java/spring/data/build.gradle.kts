plugins {
    id("java")
}

group = "site.shug.spring"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-beans:6.1.12")
    implementation("org.springframework:spring-context:6.1.12")
    implementation("org.springframework:spring-test:6.1.12")
    implementation("org.springframework.data:spring-data-jdbc:3.3.3")

    // 日志依赖
    //implementation("ch.qos.logback:logback-classic:1.5.7")
    //implementation("org.slf4j:slf4j-api:2.0.16")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation ("org.hsqldb:hsqldb:2.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

/**
 * 爆通过解析字节码来推断参数名称
 */
tasks.withType<JavaCompile>() {
    options.compilerArgs.add("-parameters")
}