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
    // 对junit5的支持
    implementation("org.springframework:spring-test:6.1.12")
    // aop
    implementation("org.springframework:spring-aop:6.1.12")
    implementation("org.springframework:spring-aspects:6.1.12")

    // 导入jakarta的注解
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    // validator的依赖
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.hibernate.validator:hibernate-validator:7.0.4.Final")
    implementation("org.glassfish:jakarta.el:4.0.2")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}