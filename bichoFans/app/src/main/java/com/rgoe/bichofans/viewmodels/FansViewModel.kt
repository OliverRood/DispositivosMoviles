package com.rgoe.bichofans.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.rgoe.bichofans.models.entities.Fan
import com.rgoe.bichofans.repositories.FanRepository

class FansViewModel(application: Application) : AndroidViewModel(application){
    private val fanRepository = FanRepository(application)

    fun getFans() : LiveData<List<Fan>> = liveData{
        val fans = fanRepository.getAllFans()

        emit(fans)
    }
}