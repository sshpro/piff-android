package com.sshpro.piff.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.sshpro.piff.business.network.NetworkService
import com.sshpro.piff.business.network.PhotoFeedNetworkEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NetworkServiceTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var moshi:Moshi

    private val server = MockServer.server

    @Before
    fun setup() {
        server.start()
        rule.inject()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldReceiveValidPhotosFeed() = runBlocking {
        server.enqueue( MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockServer.jsonResponseFlickr))

        val feed = networkService.getPhotoFeed()
        assertNotNull(feed)
        assertEquals(feed, getTestPhotoNetworkFeedEntity())
    }

    private fun getTestPhotoNetworkFeedEntity(): PhotoFeedNetworkEntity? {
        val adapter = moshi.adapter(PhotoFeedNetworkEntity::class.java)
        return adapter.fromJson(MockServer.jsonResponseFlickr)
    }
}