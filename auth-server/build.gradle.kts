val springBootVersion: String by project

plugins {
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.jetbrains.kotlin.kapt")
}

dependencies {
    implementation("org.springframework.social:spring-social-facebook:2.0.3.RELEASE")

    implementation("org.webjars:angularjs:1.4.3")
    implementation("org.webjars:bootstrap:4.1.1")
    implementation("org.webjars:webjars-locator:0.34")

    kapt("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
}
