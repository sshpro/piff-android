package com.sshpro.piff.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.sshpro.piff.business.Mapper
import com.sshpro.piff.business.domain.Photo
import com.sshpro.piff.business.network.*
import com.sshpro.piff.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class FakeNetworkModule {
    private fun baseUrl(): HttpUrl = MockServer.server.url("/test/")

    @Singleton
    @Provides
    fun providesFakeMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }

    @Singleton
    @Provides
    fun provideFakeRetrofit(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Singleton
    @Provides
    fun provideFakePhotoFeedService(retrofit: Retrofit.Builder): PhotoFeedService {
        return retrofit
            .build()
            .create(PhotoFeedService::class.java)
    }

    @Singleton
    @Provides
    fun provideFakeNetworkService(photoFeedService: PhotoFeedService): NetworkService {
        return NetworkServiceImpl(photoFeedService = photoFeedService)
    }

    @Singleton
    @Provides
    fun provideFakeNetworkMapper(): Mapper<PhotoNetworkEntity, Photo> {
        return NetworkMapper()
    }
}