package com.rgoe.bichofans.models.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rgoe.bichofans.models.dao.FanDAO
import com.rgoe.bichofans.models.entities.Fan


@Database(
    entities = [Fan::class],
    version = 1,
    exportSchema = true
)
abstract class FanDB : RoomDatabase(){

    abstract fun fanDAO() : FanDAO

}