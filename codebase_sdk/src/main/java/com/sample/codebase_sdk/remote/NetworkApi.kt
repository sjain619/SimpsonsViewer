package com.sample.codebase_sdk.remote

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface NetworkApi {
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): CharacterApi
    fun providesGson(): Gson
    fun provideRepository(
        characterApi: CharacterApi,
        ioDispatcher: CoroutineDispatcher
    ): CharacterRepository

    fun providesIODispatcher(): CoroutineDispatcher
}