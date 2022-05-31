package com.sshpro.piff.network

import okhttp3.mockwebserver.MockWebServer
import java.io.InputStreamReader

class MockServer {
    companion object {
        val server = MockWebServer()
        val jsonResponseFlickr = readJson("photo_feed_flickr.json")
        private fun readJson(path: String): String {
            return InputStreamReader(MockServer::class.java.classLoader?.getResourceAsStream(path))
                .use { it.readText() }
        }
    }
}