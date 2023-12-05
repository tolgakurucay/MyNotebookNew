// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        val nav_version = "2.7.5"
        classpath("com.google.gms:google-services:4.3.15")
    }
}
plugins {
    id("com.android.application") version ("8.0.1") apply false
    id("com.android.library") version ("8.0.1") apply false
    id("org.jetbrains.kotlin.android") version ("1.8.0") apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}