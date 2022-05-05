plugins {
    GradlePlugin.androidApplication(this)
    GradlePlugin.androidKotlin(this)
    GradlePlugin.kapt(this)
    GradlePlugin.kotlinParcelize(this)
    GradlePlugin.hiltPlugin(this)
    GradlePlugin.safeArgs(this)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = Android.Sdk.compile
    defaultConfig {
        applicationId = "com.mohamad.kmmplaces.android"
        minSdk = Android.Sdk.min
        targetSdk = Android.Sdk.target
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

}

dependencies {
    implementation(project(":coreLogic"))

    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    // Navigation
    implementation(Dependencies.Navigation.ui)
    implementation(Dependencies.Navigation.fragment)

    // DI
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    // Compose
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.constraintLayout)
    // Animations
    implementation("androidx.compose.animation:animation:${Versions.compose}")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

    // Coroutines
    implementation(Dependencies.Coroutines.android)

    // google maps
    implementation("com.google.android.gms:play-services-maps:18.0.2")

}