package com.example.martynov.data.storage.loginstorage.remotestorage.retrofit

import com.example.martynov.data.model.UserDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    fun login(@Body body: UserDTO): Call<ResponseBody>

    @POST("registration")
    fun registration(@Body body: UserDTO): Call<ResponseBody>
}