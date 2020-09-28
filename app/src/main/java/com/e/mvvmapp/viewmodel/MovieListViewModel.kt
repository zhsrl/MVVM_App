package com.e.mvvmapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.mvvmapp.model.Movie
import com.e.mvvmapp.model.MovieResponse
import com.e.mvvmapp.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(
    private val context: Context
) : ViewModel() {

    val liveData = MutableLiveData<List<Movie>>()

    fun getMovie(){
        RetrofitService
            .getMovie()
            .getPopularMovies("633b0d5400a1437826672c9966199c0b")
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if(response.isSuccessful){
                        val list = response.body()!!.getResults()
                        liveData.postValue(list)

                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(context, "ERROR - 400", Toast.LENGTH_SHORT).show()
                }

            })


    }

}