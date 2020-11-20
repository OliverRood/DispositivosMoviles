package com.rgoe.thelastairbender.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rgoe.thelastairbender.models.AvatarView
import com.rgoe.thelastairbender.repositories.LastAirbenderRepository
import kotlinx.coroutines.launch

class AvatarsActivityViewModel (application: Application): AndroidViewModel(application){

    private val lastAirbenderRepository = LastAirbenderRepository()
    val avatarsLiveData = MutableLiveData<List<AvatarView>>()
    val avatarsList = mutableListOf<AvatarView>()
    val noEnemies = listOf<String>("Ninguno")

    fun getAvatars(){
        viewModelScope.launch{
            val avatarRequest = lastAirbenderRepository.getAvatars()
            avatarsList.addAll(
                avatarRequest.map { result ->
                    Log.d("Prueba Avatar",result.toString())
                    AvatarView(
                        result._id,
                        result.photoUrl,
                        result.name,
                        result.enemies,
                        result.predecessor?.trimStart()?:"Ninguno"
                    )
                }
            )
            avatarsLiveData.postValue(avatarsList)
        }
    }
}