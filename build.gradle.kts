import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.3.21"
    maven
}

group = "ch.delconte.kscript-utils"
version = "0.3"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    api("com.github.holgerbrandl:kutils:f0cbf365ee")
    api("org.jetbrains:annotations-java5:16.0.2")
    api("com.xenomachina:kotlin-argparser:2.0.7")
    implementation(kotlin("stdlib"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}