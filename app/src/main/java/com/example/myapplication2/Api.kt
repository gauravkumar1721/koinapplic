package com.example.myapplication2

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("users")
    fun getUsers(): Call<List<Users>>
}