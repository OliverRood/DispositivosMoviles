package com.rgoe.thelastairbender.models

data class AvatarView (
    var id: String,
    var img: String,
    var name: String,
    var enemies: List<String>,
    var predecessor: String
)