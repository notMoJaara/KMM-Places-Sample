import org.gradle.kotlin.dsl.kotlin
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
}

