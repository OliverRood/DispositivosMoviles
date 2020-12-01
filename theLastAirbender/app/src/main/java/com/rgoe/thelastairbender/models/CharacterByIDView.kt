package com.rgoe.thelastairbender.models

data class CharacterByIDView (
    var id: String,
    var img: String,
    var name: String,
    var gender: String,
    var weapon: String?,
    var affiliation: String?,
    var profession: String?,
    var position: String?
)