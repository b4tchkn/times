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
        register("androidApplication") {
            id = "com.b4tchkn.primitive.androidapplication"
            implementationClass = "com.b4tchkn.times.primitive.AndroidApplicationPlugin"
        }
    }
    plugins {
        register("spotless") {
            id = "com.b4tchkn.primitive.spotless"
            implementationClass = "com.b4tchkn.times.primitive.SpotlessPlugin"
        }
    }
}
