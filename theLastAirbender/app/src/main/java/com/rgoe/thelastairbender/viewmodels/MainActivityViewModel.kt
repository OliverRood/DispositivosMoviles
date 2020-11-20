package com.rgoe.thelastairbender.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rgoe.thelastairbender.models.Character
import com.rgoe.thelastairbender.models.CharacterView
import com.rgoe.thelastairbender.repositories.LastAirbenderRepository
import kotlinx.coroutines.launch

class MainActivityViewModel (application: Application):AndroidViewModel(application){

    private val lastAirbenderRepository = LastAirbenderRepository()
    val lastAirbenderListLiveData = MutableLiveData<List<CharacterView>>()
    val lastAirbenderRandomLiveData = MutableLiveData<List<CharacterView>>()
    val listCharacters = mutableListOf<CharacterView>()
    val listCharactersRandom = mutableListOf<CharacterView>()

    fun getCharacters(){
        viewModelScope.launch {
            val characterRequest = lastAirbenderRepository.getCharacters()
            listCharacters.addAll(
                characterRequest.map { result->
                    Log.d("Prueba 2",result.toString())
                    CharacterView(
                        result._id,
                        result.photoUrl,
                        result.name,
                        result.affiliation?.trimStart()?: "Ninguna"
                    )
                }
            )
            lastAirbenderListLiveData.postValue(listCharacters)
        }
    }

    fun getCharacterRandom(){
        viewModelScope.launch {
            val characterRequest = lastAirbenderRepository.getCharacterRandom()
            listCharactersRandom.addAll(
                characterRequest.map { result->
                    Log.d("Prueba 2",result.toString())
                    CharacterView(
                        result._id,
                        result.photoUrl,
                        result.name,
                        result.affiliation?.trimStart()?: "Ninguna"
                    )
                }
            )
            lastAirbenderRandomLiveData.postValue(listCharactersRandom)
        }
    }
}