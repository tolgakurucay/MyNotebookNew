// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}
plugins {
    id ("com.android.application") version ("8.0.1") apply false
    id ("com.android.library") version ("8.0.1") apply false
    id ("org.jetbrains.kotlin.android") version ("1.8.0") apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}