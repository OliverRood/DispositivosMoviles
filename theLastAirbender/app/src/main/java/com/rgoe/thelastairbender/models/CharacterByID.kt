package com.rgoe.thelastairbender.models

import com.google.gson.annotations.SerializedName

data class CharacterByID (

    @SerializedName("allies") val allies : List<String>,
    @SerializedName("enemies") val enemies : List<String>,
    @SerializedName("_id") val _id : String,
    @SerializedName("photoUrl") val photoUrl : String,
    @SerializedName("name") val name : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("eye") val eye : String,
    @SerializedName("hair") val hair : String,
    @SerializedName("skin") val skin : String,
    @SerializedName("love") val love : String,
    @SerializedName("weapon") val weapon : String?,
    @SerializedName("profession") val profession : String?,
    @SerializedName("position") val position : String?,
    @SerializedName("predecessor") val predecessor : String?,
    @SerializedName("affiliation") val affiliation : String?,
    @SerializedName("first") val first : String?

)