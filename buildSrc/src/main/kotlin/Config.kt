object Config {

    object Android {
        const val androidMinSdkVersion = 21
        const val androidTargetSdkVersion = 34
        const val androidCompileSdkVersion = 34
    }

    object Plugins {
        const val kapt = "kapt"
        const val android = "com.android.application"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val daggerHilt = "com.google.dagger.hilt.android"
    }

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}
