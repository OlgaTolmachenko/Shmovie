package com.example.ndrly.myapplication.network

import com.example.ndrly.myapplication.beans.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {
    @GET("discover/movie")
    fun getMovies(@Query("sort_by") sortBy: String, @Query("api_key") apiKey: String, @Query("page") page: Int): Observable<Response>
}