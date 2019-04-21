val springBootVersion: String by project

apply {
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.springframework.boot")
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
    implementation("org.springframework.social:spring-social-facebook:2.0.3.RELEASE")

    implementation("org.webjars:angularjs:1.4.3")
    implementation("org.webjars:bootstrap:4.1.1")
    implementation("org.webjars:webjars-locator:0.34")
}
