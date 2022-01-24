plugins {
    id("java")
    kotlin("jvm") version "1.6.0"

    id("org.openjfx.javafxplugin") version "0.0.10"

    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.beryx.runtime") version "1.12.7"
}

group = "eu.timerertim.coobra"
version = "1.0.0"

application {
    mainClass.set("eu.timerertim.coobra.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.github.almasb:fxgl:17")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

javafx {
    version = "17"
    modules = listOf("javafx.controls", "javafx.graphics", "javafx.fxml", "javafx.media")
}

val commonArguments by lazy {
    arguments()
}

runtime {
    jpackage {
        args = argumentsRelease().map { it.toString().encodeArgument() }
    }
}

tasks {
    (run) {
        args = argumentsDevelopment().map { it.toString().encodeArgument() }
        standardInput = System.`in`
    }

    test {
        useJUnitPlatform()
    }
}

fun arguments(): List<Any> {
    return listOf(project.name, version, File("CREDITS.txt").readText())
}

fun argumentsRelease(): List<Any> = commonArguments + false

fun argumentsDevelopment(): List<Any> = commonArguments + true


fun String.encodeArgument() = replace("\n", "\\n").replace(" ", "\\\"")
