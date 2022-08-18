plugins {
    `kotlin-dsl`
}

group = "com.b4tchkn.times.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("com.android.tools.build:gradle:7.4.0-alpha03")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.7.2")
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "com.b4tchkn.primitive.androidapplication"
            implementationClass = "com.b4tchkn.times.primitive.AndroidApplicationPlugin"
        }
    }
}
