import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("org.springframework.boot") version "3.1.0"
  id("io.spring.dependency-management") version "1.0.15.RELEASE"
  kotlin("jvm") version "1.9.0-RC"
  kotlin("plugin.serialization") version "1.9.0-RC"
  kotlin("plugin.spring") version "1.9.0-RC"
}

group = "com.catchplatform"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
  mavenCentral()
}

dependencyManagement {
  imports {
    mavenBom("software.amazon.awssdk:bom:2.17.247")
  }
}

configurations {
  all {
    // for log4j2 conflict resolve
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
  }
}
//
//test {
//  useJUnitPlatform()
//}

dependencies {
  // default
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  // web
  implementation("org.springframework.boot:spring-boot-starter-web")

  // spring boot configuration annotation
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  // logging
  implementation("org.slf4j:slf4j-api")
  implementation("org.springframework.boot:spring-boot-starter-log4j2")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")

  // for mybatis
  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  implementation("com.mysql:mysql-connector-j")
  implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")

  // for json(need for kotlin data class parsing)
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.0-RC")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

  // ehcache
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("org.ehcache:ehcache:3.10.8")
  implementation("javax.cache:cache-api:1.1.1")

  // H2
  implementation("com.h2database:h2")

  // Test
  testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
  testImplementation("io.kotest:kotest-assertions-core:5.5.5")

  testImplementation("io.mockk:mockk:1.13.4")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "17"
  }
}

tasks.getByName<Jar>("jar") {
  enabled = false
}

tasks.getByName<BootJar>("bootJar") {
  enabled = true
  archiveFileName.set("app.jar")
}
