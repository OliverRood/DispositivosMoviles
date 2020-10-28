package com.rgoe.bichofans.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rgoe.bichofans.models.entities.Fan

@Dao
abstract class FanDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertFan(fan: Fan):Long

    @Query("SELECT * FROM Fan")
    abstract fun getAllFan() : LiveData<List<Fan>>

    @Query("SELECT * FROM Fan")
    abstract suspend fun getAllFanSync() : List<Fan>

}