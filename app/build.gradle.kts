plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.stopwatchapp" // Replace with your actual namespace
    compileSdk = 34 // Match the installed SDK Platform
    buildToolsVersion = "34.0.0"

    defaultConfig {
        applicationId = "com.example.stopwatchapp" // Replace with your actual applicationId
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        dataBinding = true // Enable Data Binding
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(libs.androidx.appcompat)
    // Other dependencies...
}
