
object Versions {
    val kotlin = KotlinVersion.CURRENT.toString()
    const val appCompat = "1.1.0"
    const val coroutines = "1.6.0-native-mt"
    const val ktor2 = "2.0.0-beta-1"
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

}