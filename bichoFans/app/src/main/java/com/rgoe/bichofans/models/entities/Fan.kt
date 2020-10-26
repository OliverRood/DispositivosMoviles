package com.rgoe.bichofans.models.entities

import androidx.room.Entity

@Entity
data class Fan (
    val id: Long,
    val name: String,
    val phone: String,
    val mail: String,
    val fanLvl: String,
    val jerserys: String
)