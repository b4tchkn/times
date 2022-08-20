plugins {
    id("com.b4tchkn.primitive.androidapplication")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    implementation(libs.androidxCore)
    implementation(libs.composeUi)
    implementation(libs.composeMaterial)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.androidxActivity)

    implementation(libs.junit)
    implementation(libs.androidxTestExtJunit)
    implementation(libs.androidxTestEspressoCore)
    androidTestImplementation(libs.composeUiTestJunit)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)
}