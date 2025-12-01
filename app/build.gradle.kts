import org.gradle.kotlin.dsl.implementation

plugins {
    id("com.android.application")
}

android {
    namespace = "com.shihon.hotelmanagment"
    compileSdk = 36  // updated from 34 -> 36

    defaultConfig {
        applicationId = "com.shihon.hotelmanagment"
        minSdk = 24
        targetSdk = 36  // updated from 34 -> 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    // UI
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")

    // Room Database (Java Compatible)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.gridlayout)
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // Activity
    implementation("androidx.activity:activity:1.11.0")  // explicitly added

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
