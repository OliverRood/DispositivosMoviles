package com.rgoe.thelastairbender.net.services

import com.rgoe.thelastairbender.models.Avatar
import com.rgoe.thelastairbender.models.Character
import com.rgoe.thelastairbender.models.CharacterByID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface LastAirbenderService {

    @GET("characters")
    fun getCharacters():Call<List<Character>>

    @GET("characters/random")
    fun getCharacterRandom():Call<List<Character>>

    @GET("characters/avatar")
    fun getAvatars():Call<List<Avatar>>

    @GET("characters/{name}")
    fun getCharacterByName(@Path(value="name", encoded = true) name: String):Call<List<Character>>

    @GET()
    fun getCharacterByID(@Url url: Url):Call<List<CharacterByID>>
}