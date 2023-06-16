plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {

    namespace = ("com.tolgakurucay.mynotebooknew")
    compileSdk = 33

    defaultConfig {
        applicationId = "com.tolgakurucay.mynotebooknew"

        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = ("1.0")

        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")
        vectorDrawables {
            useSupportLibrary = true
        }

        val baseUrl: String by project
        buildConfigField("String","BASE_URL",baseUrl)

    }



    buildTypes {
        named("debug"){
            isMinifyEnabled = false
            resValue("string", "notebook_app_name", "My Notebook Test")

        }
        named("release") {
            isMinifyEnabled = true
            resValue("string", "my_notebook_app_name","My Notebook")

            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )

        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.1"
    }
    packaging {
        resources {
            excludes += ("/META-INF/{AL2.0,LGPL2.1}")
        }
    }


    dependencies {

        implementation("androidx.core:core-ktx:1.10.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
        implementation("androidx.activity:activity-compose:1.7.2")
        implementation("androidx.compose:compose-bom:2023.05.01")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3:1.1.0")
        implementation("androidx.compose.material3:material3-window-size-class:1.1.0")
        implementation("com.google.accompanist:accompanist-adaptive:0.30.1")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        testImplementation("androidx.compose:compose-bom:2023.05.01")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")

        //Jetpack Navigation
        val navVersion = "2.5.3"
        implementation("androidx.navigation:navigation-compose:$navVersion")

        //Material Icons
        val materialIcons = "1.4.3"
        implementation("androidx.compose.material:material-icons-extended:$materialIcons")

        //Kotlin ktx methods
        implementation("androidx.core:core-ktx:1.10.1")

        //Dependency Injection
        implementation("com.google.dagger:hilt-android:2.44.2")
        kapt("com.google.dagger:hilt-android-compiler:2.44.2")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

        //Retrofit

        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

        //Chucker Implementation
        implementation("com.github.chuckerteam.chucker:library:3.5.2")
        releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

        //Coroutine
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

        //Firebase
        implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
        implementation("com.google.firebase:firebase-analytics-ktx")

        //Firebase Auth
        implementation("com.google.firebase:firebase-auth-ktx")

        //Permissions
        implementation("com.google.accompanist:accompanist-permissions:0.28.0")

    }
    kapt {
        correctErrorTypes = true
    }
}

