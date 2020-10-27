package com.rgoe.bichofans.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fan (
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val name: String,
    val phone: String,
    val mail: String,
    val fanLvl: String,
    val jerseys: String
)