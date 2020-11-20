package com.rgoe.thelastairbender.net

import com.rgoe.thelastairbender.BASE_URL
import com.rgoe.thelastairbender.net.services.LastAirbenderService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        @JvmStatic
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @JvmStatic
        public val lastAirbenderService = retrofit.create(LastAirbenderService::class.java)
    }
}