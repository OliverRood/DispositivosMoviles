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
    val characterLiveData = MutableLiveData<CharacterByIDView>()

    fun getCharacterByID(id: String){
        viewModelScope.launch {
            val characterRequest = lastAirbenderRepository.getCharacterByID(id)
            val character = CharacterByIDView(
                characterRequest._id,
                characterRequest.photoUrl,
                characterRequest.name,
                characterRequest.gender,
                characterRequest.weapon,
                characterRequest.affiliation,
                characterRequest.profession,
                characterRequest.position
            )
            if (character.weapon.toString().isNullOrEmpty()){
                character.weapon = "Weapon not found"
            }
            if (character.position.toString().isNullOrEmpty()) {
                character.position = "Position not found"
            }
            if (character.affiliation.toString().isNullOrEmpty()){
                character.position = "Affiliation not found"
            }
            if (character.profession.toString().isNullOrEmpty()){
                character.position = "Profession not found"
            }
            characterLiveData.postValue(character)
        }
    }

}