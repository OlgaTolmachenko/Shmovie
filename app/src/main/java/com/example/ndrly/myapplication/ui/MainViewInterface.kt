package com.example.ndrly.myapplication.ui

import com.example.ndrly.myapplication.beans.Response

interface MainViewInterface {

    fun showToast(s: String)
    fun displayMovies(response: Response)
    fun displayError(s: String)
}