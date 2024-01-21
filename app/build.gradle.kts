import org.gradle.api.JavaVersion.VERSION_17

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}


android {

    val cSdk = Integer.parseInt(findProperty("android.compileSdkVersion") as String)
    val appId = findProperty("android.applicationId") as String
    val mSdk = Integer.parseInt(findProperty("android.minSdkVersion") as String)
    val tSdk = Integer.parseInt(findProperty("android.targetSdkVersion") as String)
    val versCode = Integer.parseInt(findProperty("android.versionCode") as String)
    val versName = findProperty("android.versionName") as String
    val nameSpc = findProperty("android.namespace") as String
    val apiKey = findProperty("apiKey") as String
    val bUrlDev = findProperty("baseUrlDev") as String
    val bUrl = findProperty("baseUrl") as String
    val appName = findProperty("appName") as String
    val appNameTest = findProperty("appNameTest") as String


    namespace = nameSpc
    compileSdk = cSdk


    defaultConfig {


        applicationId = appId
        minSdk = mSdk
        targetSdk = tSdk
        versionCode = versCode
        versionName = versName
        multiDexEnabled = true

        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")
        vectorDrawables {
            useSupportLibrary = true
        }


        buildConfigField("String", "API_KEY", apiKey)


    }



    buildTypes {

        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
        named("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", bUrlDev)
            resValue("string","app_name",appNameTest)

        }


        named("release") {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL", bUrl)
            resValue("string","app_name",appName)


            @Suppress("UnstableApiUsage")
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
        sourceCompatibility = VERSION_17
        targetCompatibility = VERSION_17
    }

    @Suppress("UnstableApiUsage")
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
        implementation ("androidx.compose.ui:ui-tooling")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        testImplementation("androidx.compose:compose-bom:2023.05.01")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
        implementation("androidx.appcompat:appcompat:1.6.1")


        //Jetpack Navigation
        val navVersion = "2.7.0"
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

        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")

        //Chucker Implementation
        implementation("com.github.chuckerteam.chucker:library:3.5.2")
        //releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

        //Coroutine
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

        //Firebase
        implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
        implementation("com.google.firebase:firebase-analytics-ktx")

        //Firebase Auth
        implementation("com.google.firebase:firebase-auth-ktx")

        //Permissions
        implementation("com.google.accompanist:accompanist-permissions:0.28.0")

        //Firestore
        implementation("com.google.firebase:firebase-firestore-ktx")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-beta01")

        //Room
        val roomVersion = "2.5.2"

        implementation("androidx.room:room-runtime:$roomVersion")
        implementation("androidx.room:room-ktx:$roomVersion")
        annotationProcessor("androidx.room:room-compiler:$roomVersion")
        kapt ("androidx.room:room-compiler:$roomVersion")

        //Play Services
        implementation("com.google.android.gms:play-services-auth:20.7.0")

        //Lottie animations
        implementation("com.airbnb.android:lottie-compose:4.0.0")

        //Data store
        implementation("androidx.datastore:datastore-preferences:1.0.0")

        //coil
        implementation("io.coil-kt:coil-compose:2.5.0")


    }
    kapt {
        correctErrorTypes = true
    }
}
