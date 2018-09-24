package com.example.ndrly.myapplication.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.ndrly.myapplication.R
import com.example.ndrly.myapplication.adapter.MoviesAdapter
import com.example.ndrly.myapplication.beans.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), MainViewInterface {

    val TAG = "MV_MAIN_ACT"

    lateinit var adapter: RecyclerView.Adapter<MoviesAdapter.MoviesHolder>
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMVP()
        setupViews()
        getMovies()
    }

    override fun showToast(s: String) {
        Toast.makeText(this@MainActivity, s, Toast.LENGTH_LONG).show();
    }

    override fun displayMovies(response: Response) {
        if (response != null) {
            adapter = MoviesAdapter(response.results, this@MainActivity)
            rvMovies?.adapter = adapter
        } else {
            Log.d(TAG, "Movies response is null")
        }
    }

    override fun displayError(s: String) {
        showToast(s)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setupMVP() {
        presenter = MainPresenter(this)
    }

    private fun setupViews() {
        rvMovies?.layoutManager = LinearLayoutManager(this)
    }

    private fun getMovies() {
        presenter.getMovies()
    }
}
