package dependencies

object Square {
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiKotlin}"
    const val moshi_adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val apt_moshi_kotlin_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_moshi_converter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val test_okhttp_mock_webserver = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    const val android_test_okhttp_mock_webserver =
        "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"


}