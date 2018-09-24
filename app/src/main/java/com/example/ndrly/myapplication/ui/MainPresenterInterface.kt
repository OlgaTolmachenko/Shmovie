package com.example.ndrly.myapplication.ui

interface MainPresenterInterface {
    fun getMovies(sortQuery: String, page: Int)
}