import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
}

group = "me.mahdi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

dependencies {

    // 1. Use Guava in your implementation only:
    implementation("com.google.guava:guava:30.1-jre")
    // 2. Use Guava types in your public API:
    api("com.google.guava:guava:30.1-jre")
    implementation("io.lktk:blake3jni:0.2.2")
    implementation(group = "io.github.rctcwyvrn",name = "blake3" , version = "1.3")

}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}