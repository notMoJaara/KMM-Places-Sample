plugins {
    GradlePlugin.androidApplication(this)
    GradlePlugin.androidKotlin(this)
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
}

dependencies {
    implementation(project(":coreLogic"))
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
}