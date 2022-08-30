plugins {
    id("com.b4tchkn.primitive.androidapplication")
    id("com.b4tchkn.primitive.spotless")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.androidxCore)
    implementation(libs.composeUi)
    implementation(libs.composeMaterial)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.androidxLifecycleViewmodelCompose)
    implementation(libs.androidxActivity)

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
    implementation(libs.retrofitMock)
    testImplementation(libs.kotlinCoroutineTest)

    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)
}