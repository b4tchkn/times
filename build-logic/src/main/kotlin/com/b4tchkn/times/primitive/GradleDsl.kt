package com.b4tchkn.times.primitive

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

// fun DependencyHandlerScope.implementation(
//     artifact: Optional<Provider<MinimalExternalModuleDependency>>
// ) {
//     add("implementation", artifact.get())
// }
//
// fun DependencyHandlerScope.debugImplementation(
//     artifact: Optional<Provider<MinimalExternalModuleDependency>>
// ) {
//     add("debugImplementation", artifact.get())
// }
//
// fun DependencyHandlerScope.androidTestImplementation(
//     artifact: Optional<Provider<MinimalExternalModuleDependency>>
// ) {
//     add("androidTestImplementation", artifact.get())
// }
//
// fun DependencyHandlerScope.testImplementation(
//     artifact: Optional<Provider<MinimalExternalModuleDependency>>
// ) {
//     add("testImplementation", artifact.get())
// }

// private fun DependencyHandlerScope.api(
//     artifact: Optional<Provider<MinimalExternalModuleDependency>>
// ) {
//     add("api", artifact.get())
// }
//
// fun DependencyHandlerScope.kapt(
//     artifact: Optional<Provider<MinimalExternalModuleDependency>>
// ) {
//     add("api", artifact.get())
// }

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
