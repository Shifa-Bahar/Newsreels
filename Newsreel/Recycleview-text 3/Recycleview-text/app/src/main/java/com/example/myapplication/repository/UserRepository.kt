package com.example.myapplication.repository

import com.example.myapplication.base.BaseRepository
import com.example.myapplication.api.Api
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: Api): BaseRepository() {
    suspend fun getValue(category: String,date:String) =
        getResult { api.getValue( category,
            date ) }




    suspend fun getTodo() =
        getResult { api.getTodo() }

    suspend fun getPhoto() =
        getResult { api.getPhoto() }

}