
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.cyberquest"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.cyberquest"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    // Release APK signing configuration
    signingConfigs {
        create("release") {
            storeFile = file(
                System.getenv("CYBERQUEST_KEYSTORE_PATH")
                    ?: error("Missing keystore path")
            )

            storePassword =
                System.getenv("CYBERQUEST_STORE_PASSWORD")
                    ?: error("Missing keystore password")

            keyAlias = "cyberquest"

            keyPassword =
                System.getenv("CYBERQUEST_KEY_PASSWORD")
                    ?: error("Missing key password")
        }
    }

    buildTypes {
        release {
            signingConfig =
                signingConfigs.getByName("release")

            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)

    testImplementation(libs.junit)

    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}

