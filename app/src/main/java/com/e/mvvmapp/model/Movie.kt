package com.e.mvvmapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(

    @SerializedName("popularity") val popularity : Double,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("id") val id : Int,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("original_title") val original_title : String,
    @SerializedName("genre_ids") val genre_ids : List<Int>,
    @SerializedName("title") val title : String,

) : Serializable {
    fun getPosterPath(): String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }

}