plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}