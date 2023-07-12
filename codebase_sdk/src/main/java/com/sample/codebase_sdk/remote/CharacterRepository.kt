package com.sample.codebase_sdk.remote

import com.sample.codebase_sdk.model.CharacterProfile
import com.sample.codebase_sdk.model.mapToProfile

import com.sample.codebase_sdk.util.AppType
import com.sample.codebase_sdk.util.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface CharacterRepository{
    fun getAllCharacters(type: AppType): Flow<UIState<List<CharacterProfile>>>

}

@Singleton
class RepositoryImpl @Inject constructor(
    private val serviceApi: CharacterApi,
    private val ioDispatcher: CoroutineDispatcher
): CharacterRepository {


    override fun getAllCharacters(type: AppType): Flow<UIState<List<CharacterProfile>>> = flow {
        emit(UIState.Loading)

        try {
            val response = serviceApi.getCharacter(type.endpoint)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.Success(it.profiles.mapToProfile()))
                }
            }
        } catch (e: Exception) {
            emit(UIState.Failure(e))
        }

    }.flowOn(ioDispatcher)

}