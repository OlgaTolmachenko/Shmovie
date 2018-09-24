package com.example.ndrly.myapplication.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response(
        @SerializedName("page") @Expose val page: Int,
        @SerializedName("total_results") @Expose val totalResults: Long,
        @SerializedName("total_pages") @Expose val totalPages: Long,
        @SerializedName("results") @Expose val results: List<Movie>
)