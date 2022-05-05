
object Versions {
    val kotlin = KotlinVersion.CURRENT.toString()
    const val appCompat = "1.1.0"
    const val coroutines = "1.6.1-native-mt"
    const val ktor2 = "2.0.0-beta-1"
    const val sqlDelight = "2.0.0-alpha01"
    const val navigation = "2.4.2"
    const val compose = "1.2.0-alpha08"
    const val composeConstraintlayout = "1.0.0-beta02"
    const val activityCompose = "1.4.0"
    const val hilt = "2.39.1"
    const val mockative = "1.1.4"
    const val ktxSerialization = "1.3.2"
}


object Dependencies {

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor2}"
        const val json = "io.ktor:ktor-client-json:${Versions.ktor2}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor2}"
        const val logging = "io.ktor:ktor-client-logging:${Versions.ktor2}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor2}"
        const val utils = "io.ktor:ktor-utils:${Versions.ktor2}"
        const val mock = "io.ktor:ktor-client-mock:${Versions.ktor2}"
        const val okHttp = "io.ktor:ktor-client-okhttp:${Versions.ktor2}"
        const val iosHttp = "io.ktor:ktor-client-ios:${Versions.ktor2}"
    }

    object SqlDelight {
        const val runtime = "app.cash.sqldelight:runtime:${Versions.sqlDelight}"
        const val coroutinesExtension = "app.cash.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
        const val androidDriver = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
        const val nativeDriver = "app.cash.sqldelight:native-driver:${Versions.sqlDelight}"
        const val primitiveAdapters = "app.cash.sqldelight:primitive-adapters:${Versions.sqlDelight}"
        const val dialect = "app.cash.sqldelight:sqlite-3-24-dialect:${Versions.sqlDelight}"
        const val gradlePlugin = "app.cash.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    }

    object Navigation {
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintlayout}"
    }

    object Hilt {
        // Dagger / Hilt
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Test {
        const val mockative = "io.mockative:mockative:${Versions.mockative}"
        const val mockativeProcessor = "io.mockative:mockative-processor:${Versions.mockative}"
    }

    object Kotlinx {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.ktxSerialization}"
    }

}