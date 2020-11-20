package com.rgoe.thelastairbender.models

import com.google.gson.annotations.SerializedName

data class Character (

    @SerializedName("_id") val _id : String,
    @SerializedName("allies") val allies : List<String>,
    @SerializedName("enemies") val enemies : List<String>,
    @SerializedName("photoUrl") val photoUrl : String,
    @SerializedName("name") val name : String,
    @SerializedName("affiliation") val affiliation : String

)