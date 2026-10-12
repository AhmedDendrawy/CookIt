plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.ahmed.cookit"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.ahmed.cookit"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    //coil
    implementation("io.coil-kt:coil-compose:2.7.0")

    //Networking
    val networking = "3.0.0"
    implementation("com.squareup.retrofit2:retrofit:$networking")
    implementation("com.squareup.retrofit2:converter-gson:$networking")

    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0")

    //Room DB
    val room_version = "2.8.5"

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room3:room3-compiler:$room_version")


}