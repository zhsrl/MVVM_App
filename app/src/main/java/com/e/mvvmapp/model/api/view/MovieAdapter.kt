package com.e.mvvmapp.model.api.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.mvvmapp.model.Movie
import com.e.mvvmapp.R
import de.hdodenhof.circleimageview.CircleImageView

class MovieAdapter(var list: List<Movie>?, var context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {



    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        lateinit var movieImg: CircleImageView
        lateinit var movieName: TextView
        lateinit var moviePopularity: TextView

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie?){
            initAll()

            Glide.with(context)
                .load(movie?.getPosterPath())
                .into(movieImg)


            movieName.text = movie?.original_title
            moviePopularity.text = "Popularity: " + movie?.popularity.toString()
        }

        fun initAll() {
            movieImg = view.findViewById(R.id.movieCircleImageView)
            movieName = view.findViewById(R.id.movieNameTextView)
            moviePopularity = view.findViewById(R.id.moviePopularityTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_model, parent, false)
        val params: RecyclerView.LayoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

}