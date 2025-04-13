plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("com.google.dagger.hilt.android")  // Dagger Hilt
    kotlin("kapt")

    kotlin("plugin.serialization") version "2.1.20"
}

android {
    namespace = "com.example.livescore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.livescore"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    // Activity & Lifecycle
    implementation("androidx.compose.material3:material3:1.3.2")



    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // For clean architecture layering:
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")



    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.6.0")





    // Networking (Ktor)
    // Ktor Client Core
    implementation("io.ktor:ktor-client-core:3.1.2")

    // Ktor Client Android Engine
    implementation("io.ktor:ktor-client-android:3.1.2")

    // Ktor Client Logging
    implementation("io.ktor:ktor-client-logging:3.1.2")

    // Ktor Client Content Negotiation
    implementation("io.ktor:ktor-client-content-negotiation:3.1.2")

    // Ktor Serialization with Kotlinx JSON
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.2")

    // Ktor Client WebSockets
    implementation("io.ktor:ktor-client-websockets:3.1.2")

    // Kotlinx Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")


    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")


    implementation("io.ktor:ktor-client-okhttp:3.1.2")

    // Optional: for timeout handling
    implementation("io.ktor:ktor-client-plugins:2.3.9")


// Optional: Coil for image loading
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")


}