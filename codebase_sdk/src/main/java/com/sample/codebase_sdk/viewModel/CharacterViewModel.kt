package com.sample.codebase_sdk.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.codebase_sdk.model.CharacterProfile
import com.sample.codebase_sdk.usecases.CharacterUseCase
import com.sample.codebase_sdk.util.AppType
import com.sample.codebase_sdk.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {

    var appType: AppType? = null

    private val _characters: MutableLiveData<UIState<List<CharacterProfile>>> = MutableLiveData(UIState.Loading)
    val characters: LiveData<UIState<List<CharacterProfile>>> get() = _characters

    private val _selectedChar: MutableLiveData<CharacterProfile?> = MutableLiveData(null)
    val selectedChar: LiveData<CharacterProfile?> get()  = _selectedChar

    fun setSelectedItem(item: CharacterProfile){
        _selectedChar.value = item
    }

    init {
        getCharacterList(appType)
    }

    fun getCharacterList(charType: AppType?) {
            charType?.let {
                viewModelScope.launch {
                characterUseCase(it).collect{
                    _characters.postValue(it)
                }
            }
        }
    }



}