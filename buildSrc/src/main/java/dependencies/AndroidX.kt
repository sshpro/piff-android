package dependencies

object AndroidX {
    const val core_ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val compose_material = "androidx.compose.material:material:${Versions.compose}"

    const val compose_material_icons =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val compose_ui_tooling_preview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val compose_navigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val compose_ui_test_manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"


    const val lifecycle_runtime_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntimeKtx}"
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val ap_room = "androidx.room:room-compiler:${Versions.room}"

    const val android_test_ext_junit = "androidx.test.ext:junit:${Versions.androidJunitExt}"
    const val android_test_espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val android_test_compose_ui = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    const val debug_compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

}