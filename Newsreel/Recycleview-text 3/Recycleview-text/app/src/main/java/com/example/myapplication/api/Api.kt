package com.example.myapplication.api

import com.example.myapplication.Urls.PHOTO
import com.example.myapplication.Urls.TODO
import com.example.myapplication.Urls.USER
import com.example.myapplication.model.Photo
import com.example.myapplication.model.sports.StagesBase
import com.example.myapplication.model.TodoResponseModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
//    @GET(USER)
//    suspend fun getValue(): Response<ArrayList<UserDataModel>?>
    @Headers("X-RapidAPI-Host:livescore6.p.rapidapi.com","X-RapidAPI-Key:ef7f55f93cmshce5468e45ec9244p1010e6jsn7d9989942301")
    @GET(USER)
    suspend fun getValue(
        @Query("Category") category: String,
        @Query("Date") date: String,
    ): Response<StagesBase?>

    @GET(TODO)
    suspend fun getTodo(): Response<ArrayList<TodoResponseModel>?>


    @GET(PHOTO)
    suspend fun getPhoto(): Response<ArrayList<Photo>?>

}


