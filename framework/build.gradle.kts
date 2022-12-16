plugins {
    kotlin("kapt")
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-android-extensions")
    id ("com.google.gms.google-services")

}

android {
    namespace ="com.angelstudios.mystore"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk  =33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =  true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion  = "1.3.2"
    }
    packagingOptions {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(project(mapOf("path" to ":core")))
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("com.google.firebase:firebase-auth:21.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")


    //navigation compose
    implementation("androidx.navigation:navigation-compose:2.5.3")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    //Room
    val roomVersion = "2.4.3"

    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")


    //Gson
    implementation("com.google.code.gson:gson:2.8.9")


    // Firebase  authentication
    implementation(platform("com.google.firebase:firebase-bom:30.4.1"))
    implementation ("com.google.firebase:firebase-auth-ktx")

}

kapt {
    correctErrorTypes = true
}