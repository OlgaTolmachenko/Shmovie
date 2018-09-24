package com.example.ndrly.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.ndrly.myapplication.R
import com.example.ndrly.myapplication.beans.Movie
import com.example.ndrly.myapplication.constant.Constant

class MoviesAdapter(private val movies: List<Movie>, private val context: Context) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.row_movies, parent, false)
        return MoviesHolder(v)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.tvTitle.text = movies.get(position).title
        holder.tvOverView.text = movies.get(position).overview
        holder.tvReleaseDate.text = movies.get(position).releaseDate
        Glide.with(context)
                .load(Constant.API_IMG_URL + movies.get(position).posterPath)
                .into(holder.imgMovie)
    }
    open class MoviesHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        var tvOverView: TextView = v.findViewById(R.id.tvOverView)
        var tvReleaseDate: TextView = v.findViewById(R.id.tvReleaseDate)
        var imgMovie: ImageView = v.findViewById(R.id.ivMovie)
    }
}