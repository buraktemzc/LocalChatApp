object ApplicationId {
    val id = "com.ebt.localchatapp"
}

object Modules {
    val app = ":localchatapp"
    val data = ":data"
    val domain = ":domain"
    val presentation = ":presentation"
    val common = ":common"
}

object Versions {
    val appCompat = "1.2.0"
    val androidTestRunner = "1.2.0"
    val androidJunit = "1.1.2"
    val junitVersion = "4.13.2"
    val cardViewVersion = "1.0.0"
    val coreKtx = "1.3.2"
    val constraintLayout = "2.0.4"
    val coroutinesVersion = "1.4.2"
    val espressoCore = "3.3.0"
    val fragment = "1.3.3"
    val nav = "2.3.5"
    val glideVersion = "4.12.0"
    val gradle = "4.1.3"
    val hiltGradlePluginVersion = "2.33-beta"
    val hiltJetpackVersion = "1.0.0-alpha03"
    val hiltVersion = "2.33-beta"
    val junit = "4.13.2"
    val kotlinVersion = "1.4.31"
    val lifecycle = "2.3.1"
    val legacyVersion = "1.0.0"
    val materialDesignVersion = "1.3.0"
    val gson = "2.8.6"
    val okHttp = "4.9.0"
    val retrofit = "2.9.0"
    val logginInterceptor = "4.7.2"
    val roomVersion = "2.2.5"
    val recyclerview = "1.1.0"
    val mockWebServerCore = "4.9.0"
    val truthCore = "1.1.2"
}

object ConfigVersions {
    val compileSdkVersion = 30
    val targetSdk = 30
    val minSdk = 23
    val buildTool = "29.0.2"
    val versionCode = 1
    val versionName = "1.0"
}

object Libraries {
    // Hilt
    val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    val hiltJetpack = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltJetpackVersion}"
    val hiltJetpackCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpackVersion}"

    // For instrumentation tests
    val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    val hiltAndroidTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    // For local unit tests
    val hiltAndroidUnitTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    val hiltAndroidUnitTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    // RETROFIT
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGSONConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val httpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logginInterceptor}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    // GSON
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    // Glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    val coreKotlinCoroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    val androidKotlinCoroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
}


object AndroidLibraries {
    val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacyVersion}"
    // ANDROID
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"

    // ViewModel and LiveData
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

    // ROOM
    val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // optional - Kotlin Extensions and Coroutines support for Room
    val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
}

object TestLibraries {
    // ANDROID TEST
    val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val junitExt = "androidx.test.ext:junit:${Versions.androidJunit}"
    val junit = "junit:junit:${Versions.junitVersion}"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServerCore}"
    val truth = "com.google.truth:truth:${Versions.truthCore}"

}