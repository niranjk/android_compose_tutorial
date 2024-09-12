import java.io.FileInputStream
import java.util.Properties


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.8.10" // Or your desired version
    alias(libs.plugins.compose.compiler)
}

val keysPropertiesFile: File = rootProject.file("keys.properties")
val keysProperties = Properties()
keysProperties.load(FileInputStream(keysPropertiesFile))

android {
    namespace = "com.niranjan.khatri.androidcomposetutorial"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.niranjan.khatri.androidcomposetutorial"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        create("release") {
            keyAlias = keysProperties["keyAlias"] as String
            keyPassword = keysProperties["keyPassword"] as String
            storeFile = file(keysProperties["storeFile"] as String)
            storePassword = keysProperties["storePassword"] as String
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17" // Update to 17 or higher if your project supports it
    }
    buildFeatures {
        compose = true
    }
    // Remove composeOptions block
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    composeCompiler {
        enableStrongSkippingMode = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.compose.compiler.plugin)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation("androidx.compose.material3:material3-window-size-class:1.3.0")
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(platform(libs.kotlin.bom))
    // Remove androidx.ui.test.android as it's deprecated
    implementation(libs.androidx.ui.test.junit4.android)
    implementation(libs.places)
    implementation(libs.androidx.core.i18n)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    implementation(libs.androidx.runtime.android)

    testImplementation(libs.junit)
    // androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.compose.bom.v20230300))
    // Test rules and transitive dependencies:
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.appcompat)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.android)
    implementation(libs.image.coil)

    implementation(libs.accompanist.pager) // For pager
    implementation(libs.accompanist.pager.indicators) // For indicators
    // Room
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.runtime)
    // test
    implementation(libs.ktor.client.mock)
}