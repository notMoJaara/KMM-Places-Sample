import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec

object GradlePlugin {

    fun androidKotlin(scope: PluginDependenciesSpec) =
        scope.kotlin("android")

    fun androidApplication(scope: PluginDependenciesSpec) =
        scope.id("com.android.application")

    fun androidLibrary(scope: PluginDependenciesSpec) =
        scope.id("com.android.library")

    fun nativeCocoapods(scope: PluginDependenciesSpec) =
        scope.kotlin("native.cocoapods")

    fun multiplatform(scope: PluginDependenciesSpec) =
        scope.kotlin("multiplatform")

    fun serialization(scope: PluginDependenciesSpec) =
        scope.kotlin("plugin.serialization") version Versions.kotlin

    fun sqlDelight(scope: PluginDependenciesSpec) =
        scope.id("app.cash.sqldelight")

    fun kapt(scope: PluginDependenciesSpec) =
        scope.id("kotlin-kapt")

    fun kotlinParcelize(scope: PluginDependenciesSpec) =
        scope.id("kotlin-parcelize")

    fun hiltPlugin(scope: PluginDependenciesSpec) =
        scope.id("dagger.hilt.android.plugin")

    fun safeArgs(scope: PluginDependenciesSpec) =
        scope.id("androidx.navigation.safeargs.kotlin")

    fun ksp(scope: PluginDependenciesSpec) =
        scope.id("com.google.devtools.ksp").version("1.6.10-1.0.2")

}

