package com.e.mvvmapp.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitService{
    const val BASE_URL = "https://api.themoviedb.org/3/"


    fun getMovie(): MovieApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MovieApi::class.java)
    }


    interface MovieApi{


        @GET("movie/popular")
        fun getPopularMovies(
            @Query("api_key")apiKey: String,
        ): Call<MovieResponse>
    }

}