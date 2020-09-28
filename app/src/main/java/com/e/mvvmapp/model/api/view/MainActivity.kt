package com.e.mvvmapp.model.api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.e.mvvmapp.model.Movie
import com.e.mvvmapp.R
import com.e.mvvmapp.viewmodel.MovieListViewModel
import com.e.mvvmapp.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var movieRecyclerView: RecyclerView
    var moviesList: List<Movie> = ArrayList()
    var adapter: MovieAdapter? = null
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.main_container)

        val viewModelProviderFactory = ViewModelProviderFactory(context = this)


        movieListViewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieListViewModel::class.java)

        movieRecyclerView = findViewById(R.id.MovieRecyclerView)

        adapter = MovieAdapter(moviesList,context = applicationContext)
        movieRecyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()
        val layoutManager = LinearLayoutManager(this)
        movieRecyclerView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL


        swipeRefreshLayout.isRefreshing = true
        movieListViewModel.liveData.observe(this, Observer { result ->
            when (result){
                is MovieListViewModel.State.HideLoading -> {
                    swipeRefreshLayout.isRefreshing = false
                }
                is MovieListViewModel.State.ShowLoading -> {
                    swipeRefreshLayout.isRefreshing = true
                }
                is MovieListViewModel.State.Result -> {
                    adapter?.list = result.list
                    adapter?.notifyDataSetChanged()
                    swipeRefreshLayout.isRefreshing = false
                }
            }

        })

        //Get Popular Movie with update
        movieListViewModel.getMovie()



    }






}