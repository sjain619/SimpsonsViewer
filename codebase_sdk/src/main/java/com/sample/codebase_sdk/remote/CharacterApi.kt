package com.sample.codebase_sdk.remote

import com.sample.codebase_sdk.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("/")
     suspend fun getCharacter(
        @Query("q") title: String? = null,
        @Query("format") type: String = format
    ): Response<CharacterModel>

    companion object{
        const val format = "json"
    }
}