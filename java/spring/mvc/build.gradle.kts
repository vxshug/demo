plugins {
    id("java")
}

group = "site.shug.spring.mvc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webmvc:6.1.12")
    implementation("org.springframework:spring-beans:6.1.12")
    implementation("org.springframework:spring-context:6.1.12")
    implementation("org.springframework:spring-websocket:6.1.12")
    // Spring Security
    implementation("org.springframework.security:spring-security-web:6.3.3")
    implementation("org.springframework.security:spring-security-config:6.3.3")
    // oauth2认证服务端
    implementation("org.springframework.security:spring-security-oauth2-authorization-server:1.3.2")
    // oauth2认证客户端
    implementation("org.springframework.security:spring-security-oauth2-client:6.3.3")
    // oauth2资源服务器
    implementation("org.springframework.security:spring-security-oauth2-resource-server:6.3.3")


    // 导入jakarta的注解
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    // 加入Json支持
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")

    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")

    implementation("org.apache.tomcat.embed:tomcat-embed-core:11.0.0-M24")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.0-M24")
    implementation("org.apache.tomcat.embed:tomcat-embed-websocket:11.0.0-M24")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}