
object Versions {
    val kotlin = KotlinVersion.CURRENT.toString()
    const val appCompat = "1.1.0"
    const val coroutines = "1.6.0-native-mt"
    const val ktor2 = "2.0.0-beta-1"
    const val sqlDelight = "2.0.0-alpha01"
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

}