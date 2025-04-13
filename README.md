# SHASHANK_RANAN_CRICRADIO

## Overview

This project is an Android application that replicates the core functionality of the CricRadio app by:

- Creating a responsive UI using Jetpack Compose.
- Integrating real-time data via WebSocket.
- Fetching data from external REST APIs.

The project demonstrates clean architecture (MVVM), dependency injection with Dagger Hilt, modern networking using Ktor, and image loading with Coil.

For the additional task, a WebSocket connection is established with Postman's WebSocket Echo Service to showcase real-time data handling.

## Task Description

### Screen Recreation & API Integration

- **Screen Recreation:**  
  - Recreate the provided Figma design using Jetpack Compose.
  - Ensure the UI is adaptive and follows modern Android design guidelines.
  
- **API Integration:**  
  Two APIs are called on startup to populate the UI:
  - **Mini Scorecard API:**  
    - **Base URL:** `http://3.6.243.12:5001`  
    - **Endpoint:** `/api/v2/match/mini-match-card`  
    - **Parameter:** `key=SA_vs_SL_2024-12-05_1732276435.300452`
  - **Venue Info API:**  
    - **Base URL:** `http://3.6.243.12:5001`  
    - **Endpoint:** `/api/v2/match/venue-info`  
    - **Parameter:** `key=SA_vs_SL_2024-12-05_1732276435.300452`
  - **Authorization Header:**  
    All API calls include:  
    ```
    Authorization: "Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM="
    ```

### Additional Task – Socket Connection

- **Socket Server Details:**  
  - **URL:** `wss://ws.postmanecho.com/raw`
  
- **Functionality:**  
  - Connect to the WebSocket server using Ktor.
  - Send a test message to the server.
  - Listen for and display the echoed response on both the console and UI.
  - Handle connection errors with retry logic.

A dedicated Compose screen demonstrates this functionality with an input field, a send button, and a display area for the response.

## Best Practices & Architecture

- **Clean Architecture (MVVM):**  
  The project is organized into distinct layers: Presentation, ViewModel, Domain, and Data.
  
- **Dependency Injection:**  
  Dagger Hilt is used throughout the app for dependency injection.
  
- **Networking:**  
  Ktor handles REST API calls and WebSocket communication, including proper header management and error handling.
  
- **UI Design:**  
  UI components are built using Jetpack Compose, ensuring a modular and responsive design.
  
- **Real-Time Data:**  
  A WebSocket connection to Postman's Echo Service demonstrates real-time data handling.

## Technical Details

- **Android Configuration:**  
  - **minSdk:** 24  
  - **compileSdk & targetSdk:** 35
- **Languages & Frameworks:**  
  - **Kotlin** with **Jetpack Compose**
  - **Ktor Client** for networking (version 3.1.2)
  - **Dagger Hilt** for dependency injection (version 2.51.1)
  - **Coil** for image loading (coil-compose 2.7.0, coil-network-okhttp 3.1.0)
- **Gradle Plugins & Dependencies:**  
  See the Gradle Build Configuration section below.

## Gradle Build Configuration

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    // Core Android & Compose Libraries
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

    // Material3 & Compose
    implementation("androidx.compose.material3:material3:1.3.2")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // ViewModel & Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.6.0")

    // Ktor Networking (API & WebSockets)
    implementation("io.ktor:ktor-client-core:3.1.2")
    implementation("io.ktor:ktor-client-android:3.1.2")
    implementation("io.ktor:ktor-client-okhttp:3.1.2")
    implementation("io.ktor:ktor-client-logging:3.1.2")
    implementation("io.ktor:ktor-client-content-negotiation:3.1.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.2")
    implementation("io.ktor:ktor-client-websockets:3.1.2")
    implementation("io.ktor:ktor-client-plugins:2.3.9")
    
    // Kotlinx Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Coil for Image Loading
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("io.coil-kt.coil-network-okhttp:3.1.0")
}
