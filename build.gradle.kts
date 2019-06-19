plugins {
    `java-library`
    kotlin("jvm") version "1.3.21"
    maven
}

group = "ch.delconte.kscript-utils"
version = "0.3.1"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    api("org.jetbrains:annotations-java5:16.0.2")
    runtime("com.xenomachina:kotlin-argparser:2.0.7")
    implementation(kotlin("stdlib"))
}