buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.25")
        classpath("com.android.tools.build:gradle:8.13.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
        classpath ("com.google.gms:google-services:4.4.1")
        // Add the Crashlytics Gradle plugin
        classpath ("com.google.firebase:firebase-crashlytics-gradle:3.0.2")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}