import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by project
val springBootVersion: String by project
val springCloudVersion: String by project

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("idea")
}

allprojects {
    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("idea")
    }

    group = "pl.michalregulski"
    version = "0.0.1.SNAPSHOT"

    repositories {
        jcenter()
        mavenCentral()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
        constraints {
            implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
            implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:$springBootVersion")
        }

        implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"))

        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.cloud:spring-cloud-starter-oauth2") {
            exclude(group = "org.springframework.security.oauth.boot", module = "spring-security-oauth2-autoconfigure")
        }
        implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure") {
            exclude(group = "com.sun.xml.bind", module = "jaxb-core")
            exclude(group = "com.sun.xml.bind", module = "jaxb-impl")
        }
        implementation("javax.xml.bind:jaxb-api")
        implementation("org.glassfish.jaxb:jaxb-runtime")

        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    configurations {
        implementation {
            resolutionStrategy.failOnVersionConflict()
        }
    }
}
