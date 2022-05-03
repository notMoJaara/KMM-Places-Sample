
object Versions {
    val kotlin = KotlinVersion.CURRENT.toString()
    const val appCompat = "1.1.0"
    const val coroutines = "1.6.0-native-mt"
}


object Dependencies {

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

}