package com.rgoe.bichofans.repositories

import android.content.Context
import com.rgoe.bichofans.models.entities.Fan
import com.rgoe.bichofans.models.roomdb.FanDB

class FanRepository(context: Context) {
    private val fanDB = FanDB.getInstance(context)
    private val fanDAO = fanDB.fanDAO()

    suspend fun insertFan(fan: Fan){
        fanDAO.insertFan(fan)
    }

    suspend fun getAllFans(): List<Fan>{
        return fanDAO.getAllFanSync()
    }
}