package com.rgoe.thelastairbender.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rgoe.thelastairbender.models.CharacterByID
import com.rgoe.thelastairbender.models.CharacterByIDView
import com.rgoe.thelastairbender.repositories.LastAirbenderRepository
import kotlinx.coroutines.launch

class CharacterActivityViewModal (application: Application): AndroidViewModel(application) {

    private val lastAirbenderRepository = LastAirbenderRepository()
    val characterLiveData = MutableLiveData<CharacterByID>()

    fun getCharacterByID(id: String){
        viewModelScope.launch {
            val characterRequest = lastAirbenderRepository.getCharacterByID(id)
            characterLiveData.postValue(characterRequest)
        }
    }

}