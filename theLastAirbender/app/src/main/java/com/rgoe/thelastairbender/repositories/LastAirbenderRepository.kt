package com.rgoe.thelastairbender.repositories

import android.util.Log
import com.rgoe.thelastairbender.models.Avatar
import com.rgoe.thelastairbender.models.Character
import com.rgoe.thelastairbender.net.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LastAirbenderRepository {

    suspend fun getCharacters():List<Character>{
        return suspendCoroutine{
            RetrofitInstance.lastAirbenderService.getCharacters().enqueue(object:
            Callback<List<Character>>{
                override fun onResponse(
                    call: Call<List<Character>>,
                    response: Response<List<Character>>
                ) {
                    Log.d("Prueba Todos",response.body().toString())
                    it.resume(response.body()!!)
                }

                override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getCharacterRandom():List<Character>{
        return suspendCoroutine{
            RetrofitInstance.lastAirbenderService.getCharacterRandom().enqueue(object:
                Callback<List<Character>>{
                override fun onResponse(
                    call: Call<List<Character>>,
                    response: Response<List<Character>>
                ) {
                    Log.d("Prueba Random",response.body().toString())
                    it.resume(response.body()!!)
                }

                override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getAvatars():List<Avatar>{
        return suspendCoroutine {
            RetrofitInstance.lastAirbenderService.getAvatars().enqueue(object :
                Callback<List<Avatar>>{
                override fun onResponse(
                    call: Call<List<Avatar>>,
                    response: Response<List<Avatar>>
                ) {
                    it.resume(response.body()!!)
                }

                override fun onFailure(call: Call<List<Avatar>>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }
}