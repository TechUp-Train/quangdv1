import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    id("kotlin-parcelize")
}

val buildConfigFields =
    mapOf(
        "API_KEY" to "API_KEY",
        "PUBLIC_KEY" to "PUBLIC_KEY",
        "BUNDLE_ID" to "BUNDLE_ID",
        "APP_NAME_VALUE" to "APP_NAME",
        "API_TOKEN" to "API_TOKEN",
        "DEVICE_ID" to "DEVICE_ID",
        "APP_VERSION_VALUE" to "APP_VERSION",
        "BASE_URL" to "BASE_URL",
        "TIMESTAMP_BASE_URL" to "TIMESTAMP_BASE_URL",
    )

kotlin {
    jvmToolchain(17)
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            freeCompilerArgs.addAll(
                "-P",
                "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=the.package.containing.annotation.CommonParcelize",
            )
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            implementation(libs.bundles.ktor)
            implementation(libs.bundles.navigation3)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.filekit.dialogs.compose)

            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.atomicfu)

            implementation(libs.material.icons.extended)

            implementation(libs.konnectivity)

            implementation(libs.kmp.shimmer.compose)

            implementation(libs.kermit)
        }
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().configureEach {
        binaries.all {
            linkerOpts("-framework", "SystemConfiguration")
        }
    }
}

android {
    namespace = "com.example.techup_miniproject_quangdv1"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = "com.example.techup_miniproject_quangdv1"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"

        fun String.escape(): String =
            replace("\\", "\\\\")
                .replace("\"", "\\\"")

        for ((fieldName, propertyKey) in buildConfigFields) {
            val value = project.findProperty(propertyKey)?.toString()?.escape() ?: ""
            buildConfigField("String", fieldName, "\"$value\"")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}
