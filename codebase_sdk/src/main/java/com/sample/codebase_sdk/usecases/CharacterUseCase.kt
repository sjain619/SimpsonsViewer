package com.sample.codebase_sdk.usecases

import com.sample.codebase_sdk.model.CharacterProfile
import com.sample.codebase_sdk.remote.CharacterRepository
import com.sample.codebase_sdk.util.AppType
import com.sample.codebase_sdk.util.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(appType: AppType): Flow<UIState<List<CharacterProfile>>> =
        repository.getAllCharacters(appType)
}