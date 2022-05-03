plugins {
    GradlePlugin.multiplatform(this)
    GradlePlugin.androidLibrary(this)
    GradlePlugin.sqlDelight(this)
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
                implementation(project(":util"))

                // coroutines
                implementation(Dependencies.Coroutines.core) {
                    version {
                        // strictly using the native-mt version on coroutines
                        strictly(Versions.coroutines)
                    }
                }
                implementation(Dependencies.SqlDelight.runtime)
                implementation(Dependencies.SqlDelight.coroutinesExtension)
                implementation(Dependencies.SqlDelight.primitiveAdapters)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.SqlDelight.androidDriver)
                implementation("androidx.sqlite:sqlite:2.0.1")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(Dependencies.SqlDelight.nativeDriver)
            }
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

android {
    compileSdk = Android.Sdk.compile
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Android.Sdk.min
        targetSdk = Android.Sdk.target
    }
}

sqldelight {
    database("PoiDatabase") {
        packageName = "com.mohamad.kmmplaces.localStorage"
        sourceFolders = listOf("poi_db")
    }
}