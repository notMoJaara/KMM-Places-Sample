plugins {
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.0" apply false
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath(Dependencies.Navigation.gradlePlugin)
        classpath(Dependencies.Hilt.gradlePlugin)
        classpath(Dependencies.SqlDelight.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}