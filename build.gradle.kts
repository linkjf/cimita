// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Config.Plugins.android) version Versions.androidApplicationPluginVersion apply false
    id(Config.Plugins.kotlinAndroid) version Versions.kotlinAndroidPluginVersion apply false
    id(Config.Plugins.daggerHilt) version Versions.daggerHiltAndroidPluginVersion apply false
}
