package com.rgoe.bichofans.models.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rgoe.bichofans.models.dao.FanDAO
import com.rgoe.bichofans.models.entities.Fan


@Database(
    entities = [Fan::class],
    version = 1,
    exportSchema = true
)
abstract class FanDB : RoomDatabase(){

    abstract fun fanDAO() : FanDAO

    companion object {
        @Synchronized
        fun getInstance(context: Context): FanDB {
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    FanDB::class.java,
                    "fan.db"
                ).allowMainThreadQueries().build()

                instance?.initDB()
            }
            return instance as FanDB
        }
    }

    fun initDB(){
        val fanDAO = fanDAO()
        if (fanDAO.getAllFans().isEmpty()){
            fanDAO.insertFan(
                Fan(
                    name = "Oli",
                    phone = "667",
                    mail = "oli@siu.com",
                    fanLvl = "Bichologo Pro",
                    jerseys = "Real Madrid"
                )
            )
            fanDAO.insertFan(
                Fan(
                    name = "Nora",
                    phone = "669",
                    mail = "nora@siu.com",
                    fanLvl = "Bichologo Pro",
                    jerseys = "Juventus"
                )
            )
        }
    }
}

@Volatile
private var instance: FanDB?= null