package dependencies

object Google {
    const val hilt_android_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val ap_hilt =  "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val ar_core = "com.google.ar:core:${Versions.arCore}"

    const val test_hilt = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val ap_test_hilt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    const val android_test_hilt = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val ap_android_test_hilt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"


}