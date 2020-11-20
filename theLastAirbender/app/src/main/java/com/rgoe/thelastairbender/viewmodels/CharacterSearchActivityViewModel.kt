package com.rgoe.thelastairbender.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rgoe.thelastairbender.models.CharacterView
import com.rgoe.thelastairbender.repositories.LastAirbenderRepository
import kotlinx.coroutines.launch
import retrofit2.http.Url

class CharacterSearchActivityViewModel (application: Application): AndroidViewModel(application){

    private val lastAirbenderRepository = LastAirbenderRepository()
    val characterSearchLiveData = MutableLiveData<List<CharacterView>>()
    val listCharacterSearch = mutableListOf<CharacterView>()

    fun getCharacterSearch(name: String){
        viewModelScope.launch{
            val characterSearchRequest = lastAirbenderRepository.getCharacterSearch(name)
            listCharacterSearch.addAll(
                characterSearchRequest.map { result ->
                    CharacterView(
                        result._id,
                        result.photoUrl,
                        result.name,
                        result.affiliation?.trimStart()?:"Ninguna"
                    )
                }
            )
            characterSearchLiveData.postValue(listCharacterSearch)
        }
    }
}