import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    GradlePlugin.multiplatform(this)
    GradlePlugin.androidLibrary(this)
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Coroutines.core) {
                    version {
                        // strictly using the native-mt version on coroutines
                        strictly(Versions.coroutines)
                    }
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

val fourSquareKey: String = gradleLocalProperties(rootDir).getProperty("fourSquareKey")

android {
    compileSdk = Android.Sdk.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Android.Sdk.min
        targetSdk = Android.Sdk.target
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "fourSquareKey", fourSquareKey)
        }
        getByName("release") {
            buildConfigField("String", "fourSquareKey", fourSquareKey)
        }
    }
}
