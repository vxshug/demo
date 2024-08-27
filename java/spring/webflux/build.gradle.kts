plugins {
    id("java")
}

group = "site.shug.spring.webflux"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.projectreactor.netty:reactor-netty:1.1.22")
    implementation("org.springframework:spring-webflux:6.1.12")
    implementation("org.springframework:spring-beans:6.1.12")
    implementation("org.springframework:spring-context:6.1.12")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}