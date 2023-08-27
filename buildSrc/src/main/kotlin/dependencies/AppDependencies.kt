package dependencies

object AppDependencies {
    const val coreKtx = Dependencies.CoreDep.coreKtx
    const val composeBoom = Dependencies.ComposeDep.composeBoom


    val lifeCycle = listOf(
        Dependencies.LifeCycleDep.lifeCycleRuntime,
        Dependencies.LifeCycleDep.viewmodelCompose,
        Dependencies.LifeCycleDep.composeLiveData
    )

    val compose = listOf(
        Dependencies.ComposeDep.activityCompose,
        Dependencies.ComposeDep.composeUI,
        Dependencies.ComposeDep.composeGraphics,
        Dependencies.ComposeDep.composeUiPreview,
        Dependencies.ComposeDep.composeMaterial3
    )

     const val coroutines = Dependencies.CoroutineDep.courotines

    const val hilt = Dependencies.HiltDep.hiltAndroid
    const val kaptHiltCompiler = Dependencies.HiltDep.hiltAndroidCompiler

    val retrofit = listOf(
        Dependencies.RetrofitDep.retrofit,
        Dependencies.RetrofitDep.retrofitGsonConverter,
        Dependencies.RetrofitDep.loggingInterceptor
    )

    val glide = listOf(
        Dependencies.GlideDep.glide,
        Dependencies.GlideDep.glideCompose
    )

    const val glideAnnotation = Dependencies.GlideDep.glideCompiler

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val exTestJunit = Dependencies.TestDep.exTestJunit
        const val espresso = Dependencies.TestDep.espresso
        const val composeBoomTest = Dependencies.TestDep.composeBoomTest
        const val composeJunit = Dependencies.TestDep.composeJunit
        const val composeUITooling = Dependencies.TestDep.composeUITooling
        const val composeManifestTest = Dependencies.TestDep.composeManifestTest
    }
}
