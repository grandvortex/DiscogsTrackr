plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.grandvortex.discogstrackr"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.grandvortex.discogstrackr"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        // Designate a directory for Room database schema for versioning (useful when updating database version for comparison)
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

        // Discogs key and secret to use the service
        buildConfigField("String", "KEY", "\"ABvycztVyPKHEkutPDNQ\"")
        buildConfigField("String", "SECRET", "\"xtjhIkDzviXawzrOPTfkxXArxLeLZiso\"")
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false

            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type. Make sure to use a build
            // variant with `isDebuggable=false`.
            isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true

            buildConfigField("String", "BASE_URL", "\"https://api.discogs.com/\"")

            // Includes the default ProGuard rules files that are packaged with
            // the Android Gradle plugin. To learn more, go to the section about
            // R8 configuration files.
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            buildConfigField("String", "BASE_URL", "\"https://api.discogs.com/\"")
        }
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    packaging {
        resources.excludes += "/META-INF/AL2.0"
        resources.excludes += "/META-INF/LGPL2.1"
    }
}

dependencies {
    // Bill Of Materials for compose
    val composeBOM = platform(libs.compose.bom)

    // Bill of Materials for okhttp
    val okhttpBOM = platform(libs.okhttp.bom)

    // Compose
    implementation(composeBOM)
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material:material-icons-extended")
    implementation(libs.navigation.compose)
    implementation(libs.activity.compose)

    // Compose Testing
    androidTestImplementation(composeBOM)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Room
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

    // Room Testing
    testImplementation(libs.room.testing)

    // Lifecycle Scopes and Architectural Components
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.runtime.ktx)

    // Coil
    implementation(libs.coil.compose)

    // Coroutines
    implementation(libs.coroutines.kotlinx.core)
    implementation(libs.coroutines.kotlinx.android)

    // Android
    implementation(libs.android.core.ktx)
    implementation(libs.android.appcompat)

    // Kotlin Date and Time
    implementation(libs.kotlin.datetime)

    // OKHTTP
    implementation(okhttpBOM)
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    // Moshi
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.moshi.kotlin)

    // Hilt
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)

    // Hilt for instrumentation tests
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)

    // Hilt for local unit tests
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.compiler)

    // Android Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.android.test.junit)
    androidTestImplementation(libs.android.test.espresso.core)
}
