plugins {
    id("com.b4tchkn.primitive.androidapplication")
    id("com.b4tchkn.primitive.spotless")
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.androidxCore)
    implementation(libs.composeUi)
    implementation(libs.coilCompose)
    implementation(libs.accompanistPager)
    implementation(libs.accompanistSwiperefresh)
    implementation(libs.composeMaterial)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.androidxLifecycleViewmodelCompose)
    implementation(libs.androidxActivity)
    implementation(libs.composeDestinationsCore)
    ksp(libs.composeDestinationsKsp)
    implementation(libs.hiltNavigationComopse)

    implementation(libs.retrofit)
    implementation(libs.retrofitConverterSimplexml)
    implementation(libs.okhttp)

    implementation(libs.kotlinSerializationJson)
    implementation(libs.retrofitKotlinSerializationConverter)

    implementation(libs.daggerHiltAndroid)
    kapt(libs.daggerHiltAndroidCompiler)

    implementation(libs.junit)
    implementation(libs.androidxTestExtJunit)
    implementation(libs.androidxTestEspressoCore)
    androidTestImplementation(libs.composeUiTestJunit)
    testImplementation(libs.kotlinCoroutineTest)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockitoInline)

    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)
}