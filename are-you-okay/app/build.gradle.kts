plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("app.cash.paparazzi")
}

android {
    namespace = "app.areyouokay"
    compileSdk = 35

    defaultConfig {
        applicationId = "app.areyouokay"
        minSdk = 26
        targetSdk = 35
        versionCode = 3
        versionName = "0.2.1"
    }

    // Optional release signing from environment variables (used by CI when secrets exist).
    val keystorePath = System.getenv("AYO_KEYSTORE_PATH")
    signingConfigs {
        if (keystorePath != null) {
            create("release") {
                storeFile = file(keystorePath)
                storePassword = System.getenv("AYO_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("AYO_KEY_ALIAS")
                keyPassword = System.getenv("AYO_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            // R8 strips unused code and resources; there is no reflection to keep.
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            if (keystorePath != null) signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    lint {
        // Lint fails the build on errors; known, intentional warnings are listed in lint.xml.
        lintConfig = file("lint.xml")
        warningsAsErrors = false
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}
