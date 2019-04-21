apply {
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.springframework.boot")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}
