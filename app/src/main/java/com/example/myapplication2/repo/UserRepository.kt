package com.example.myapplication2.repo

import com.example.myapplication2.Api

class UserRepository(private val api: Api) {
    fun getAllUsers() = api.getUsers()

}