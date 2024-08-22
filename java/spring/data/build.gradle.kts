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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation ("org.hsqldb:hsqldb:2.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}