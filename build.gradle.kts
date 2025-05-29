// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    dependencies {
        // ... other classpath dependencies ...
        //classpath("com.android.tools.build:gradle:8.9.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50") // Use the latest version
    }
}