package com.sshpro.piff.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.sshpro.piff.business.Mapper
import com.sshpro.piff.business.domain.Photo
import com.sshpro.piff.business.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Singleton
    @Provides
    fun providePhotoFeedService(retrofit: Retrofit.Builder): PhotoFeedService {
        return retrofit
            .build()
            .create(PhotoFeedService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkService(photoFeedService: PhotoFeedService): NetworkService {
        return NetworkServiceImpl(photoFeedService = photoFeedService)
    }

    @Singleton
    @Provides
    fun provideNetworkMapper(): Mapper<PhotoNetworkEntity, Photo> {
        return NetworkMapper()
    }
}