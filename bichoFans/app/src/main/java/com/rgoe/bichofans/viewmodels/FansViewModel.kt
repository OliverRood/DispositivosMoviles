package com.rgoe.bichofans.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rgoe.bichofans.models.entities.Fan
import com.rgoe.bichofans.models.roomdb.FanDB

class FansViewModel(application: Application) : AndroidViewModel(application){
    private val fanDB = FanDB.getInstance(application)
    private val fanDAO = fanDB.fanDAO()

    fun getFans() : LiveData<List<Fan>>{
         return fanDAO.getAllFan()
    }
}