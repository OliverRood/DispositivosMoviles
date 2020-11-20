package com.rgoe.thelastairbender.net.services

import com.rgoe.thelastairbender.models.Avatar
import com.rgoe.thelastairbender.models.Character
import com.rgoe.thelastairbender.models.CharacterByID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface LastAirbenderService {

    @GET("characters")
    fun getCharacters():Call<List<Character>>

    @GET("characters/random")
    fun getCharacterRandom():Call<List<Character>>

    @GET("characters/avatar")
    fun getAvatars():Call<List<Avatar>>

    @GET("characters")
    fun getCharacterByName(@Query("name") name:String):Call<List<Character>>

    @GET("characters/{id}")
    fun getCharacterByID(@Path("id") id:String):Call<CharacterByID>
}