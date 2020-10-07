package com.e.mvvmapp.model

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("page")
    private val page = 1

    @SerializedName("total_results")
    private val total_results = 10000

    @SerializedName("total_pages")
    private val total_pages = 500

    @SerializedName("results")
    private lateinit var results : List<Movie>

    fun getResults(): List<Movie>{
        return results
    }
}