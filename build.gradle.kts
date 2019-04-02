import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.3.21"
    maven
}

group = "ch.delconte.kscript-utils"
version = "0.2"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("de.mpicbg.scicomp:kutils:0.11")
    api("de.mpicbg.scicomp:kutils:0.11")
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}