import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion: String by project
val springCloudVersion: String by project
val kotlinVersion: String by project

plugins {
    id("java")
    id("idea")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.spring") apply false
    id("org.springframework.boot") apply false
}

allprojects {
    apply {
        plugin("java-library")
        plugin("kotlin")
        plugin("idea")
    }

    group = "pl.michalregulski"
    version = "0.0.1.SNAPSHOT"

    repositories {
        jcenter()
        mavenCentral()
        maven(url = "http://repo.spring.io/milestone")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    idea {
        module {
            inheritOutputDirs = true
        }
    }

    val kotlinOptions: KotlinJvmOptions.() -> Unit = {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions(kotlinOptions)

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions(kotlinOptions)

    dependencies {
        implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"))
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
        implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

        testImplementation("org.springframework.boot:spring-boot-starter-test")

        constraints {
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
            implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
            implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:$springBootVersion")
        }
    }

    configurations {
        implementation {
            resolutionStrategy.failOnVersionConflict()
        }
    }
}
