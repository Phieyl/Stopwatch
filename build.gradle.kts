plugins {
    id("com.android.application") version "8.7.2" apply false
    kotlin("android") version "1.9.10" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
