package com.example.ndrly.myapplication.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie(

        @SerializedName("vote_count") @Expose val voteCount: Int,
        @SerializedName("id") @Expose val id: Long,
        @SerializedName("vote_average") @Expose val voteAvg: Int,
        @SerializedName("title") @Expose val title: String,
        @SerializedName("poster_path") @Expose val posterPath: String,
        @SerializedName("original_language") @Expose val origLang: String,
        @SerializedName("genre_ids") @Expose val genreIds: List<Int>,
        @SerializedName("adult") @Expose val isAdult: Boolean,
        @SerializedName("overview") @Expose val overview: String,
        @SerializedName("release_date") @Expose val releaseDate: String
)