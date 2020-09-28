package com.e.mvvmapp.model.api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.mvvmapp.model.Movie
import com.e.mvvmapp.R
import com.e.mvvmapp.viewmodel.MovieListViewModel
import com.e.mvvmapp.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var movieRecyclerView: RecyclerView
    var moviesList: List<Movie> = ArrayList()
    var adapter: MovieAdapter? = null

    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelProviderFactory = ViewModelProviderFactory(context = this)


        movieListViewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieListViewModel::class.java)

        movieRecyclerView = findViewById(R.id.MovieRecyclerView)

        adapter = MovieAdapter(moviesList,context = applicationContext)
        movieRecyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()
        val layoutManager = LinearLayoutManager(this)
        movieRecyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        movieListViewModel.liveData.observe(this, Observer { result ->
            adapter?.list = result
            adapter?.notifyDataSetChanged()
        })

        //Get Popular Movie with update
        movieListViewModel.getMovie()


    }






}