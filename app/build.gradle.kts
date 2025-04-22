import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //google analytics
    id("com.google.gms.google-services")

    //openapi
    alias(libs.plugins.openApi.generator)
}

android {
    namespace = "de.sortingfarmer.manju"
    compileSdk = 35

    defaultConfig {
        applicationId = "de.sortingfarmer.manju"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    sourceSets {
        getByName("main") {
            val dirs = listOf("src/main/res", "src/main/icon-res", "src/main/flag-res")
            res.srcDirs(*dirs.toTypedArray())
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.converter.scalars)
    implementation(libs.moshi)

    //coil
    implementation(libs.coil.compose)

    //Google firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // Typed DataStore (Typed API surface, such as Proto)
    implementation(libs.androidx.datastore)
}

openApiGenerate {
    skipValidateSpec.set(true)
    generatorName.set("kotlin")
    packageName.set("de.sortingfarmer.manju.openapi")
    generateApiTests.set(false)
    generateModelTests.set(false)
    library.set("jvm-retrofit2")
    inputSpec.set("$rootDir/openapi/api.yaml")
    configOptions.set(
        mapOf(
            "serializationLibrary" to "gson",
            "useCoroutines" to "true",
        )
    )
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("${layout.buildDirectory.get()}/generate-resources/main/src")
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn("openApiGenerate")
}