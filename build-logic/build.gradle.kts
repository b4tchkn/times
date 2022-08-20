plugins {
    `kotlin-dsl`
}

group = "com.b4tchkn.times.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.spotlessGradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "com.b4tchkn.primitive.androidapplication"
            implementationClass = "com.b4tchkn.times.primitive.AndroidApplicationPlugin"
        }
    }
}
