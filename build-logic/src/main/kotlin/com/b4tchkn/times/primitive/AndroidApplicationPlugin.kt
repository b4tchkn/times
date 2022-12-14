package com.b4tchkn.times.primitive

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
                apply("kotlinx-serialization")
                apply("kotlin-kapt")
                apply("dagger.hilt.android.plugin")
            }

            extensions.configure<BaseAppModuleExtension> {
                compileSdk = 33

                defaultConfig {
                    applicationId = "com.b4tchkn.times"
                    minSdk = 23
                    targetSdk = 32
                    versionCode = 1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }

                buildTypes {
                    release {
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                    isCoreLibraryDesugaringEnabled = true
                }

                kotlinOptions {
                    // Treat all Kotlin warnings as errors (disabled by default)
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

                    freeCompilerArgs = freeCompilerArgs + listOf()

                    jvmTarget = JavaVersion.VERSION_1_8.toString()
                }

                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

                dependencies {
                    add("coreLibraryDesugaring", libs.findLibrary("androidDesugarJdkLibs").get())
                }

                packagingOptions {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}
