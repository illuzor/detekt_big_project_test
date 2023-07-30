plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.illuzor.dbpt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

val detekt by configurations.creating

val detektTask = tasks.register<JavaExec>("detekt") {
    mainClass = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = detekt

    args(listOf("-i", rootProject.projectDir.resolve("big_project"), "-ex", "**/build/**"))
}

val detektNoExcludesTask = tasks.register<JavaExec>("detektNoExcludes") {
    mainClass = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = detekt

    args(listOf("-i", rootProject.projectDir.resolve("big_project")))
}

dependencies {
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.23.0")
}


