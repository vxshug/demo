plugins {
    id("java")
    id("war")
}

group = "site.shug.spring.mvc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // spring mvc依赖
    implementation("org.springframework:spring-webmvc:6.1.12")
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:11.0.0-M24")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.0-M24")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}