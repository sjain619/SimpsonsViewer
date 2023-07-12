package com.sample.codebase_sdk.di

import com.google.gson.Gson
import com.sample.codebase_sdk.remote.NetworkApi
import com.sample.codebase_sdk.remote.CharacterApi
import com.sample.codebase_sdk.remote.CharacterRepository
import com.sample.codebase_sdk.remote.RepositoryImpl
import com.sample.codebase_sdk.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule : NetworkApi {

    @Provides
    @Singleton
    override fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    override fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    override fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): CharacterApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
            .create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    override fun provideRepository(
        characterApi: CharacterApi,
        ioDispatcher: CoroutineDispatcher
    ): CharacterRepository {
        return RepositoryImpl(characterApi, ioDispatcher)
    }

    @Provides
    @Singleton
    override fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    override fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}