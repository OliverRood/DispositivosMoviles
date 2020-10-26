package com.rgoe.bichofans.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rgoe.bichofans.models.entities.Fan

@Dao
abstract class FanDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertFan(content: Fan)

    @Query("SELECT * FROM Fan")
    abstract fun getAllFans()

}