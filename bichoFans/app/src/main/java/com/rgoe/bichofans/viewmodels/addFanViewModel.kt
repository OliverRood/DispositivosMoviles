package com.rgoe.bichofans.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.rgoe.bichofans.models.entities.Fan
import com.rgoe.bichofans.repositories.FanRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class addFanViewModel(application: Application):AndroidViewModel(application){
    private val fanRepository = FanRepository(application)
    private val TAG = addFanViewModel::class.java.name
    private val insertLiveData = MutableLiveData<Boolean>()

    fun insertFan(fan: Fan) = viewModelScope.launch {
        try {
            fanRepository.insertFan(fan)
            insertLiveData.postValue(true)
        } catch (ex: Exception){
            Log.e(TAG, ex.message, ex)
            insertLiveData.postValue(false)
        }
    }

    fun notifyInsertFan() : LiveData<Boolean> = insertLiveData

    fun getAllFans() : LiveData<List<Fan>> = liveData {
        emit(fanRepository.getAllFans())
    }
}