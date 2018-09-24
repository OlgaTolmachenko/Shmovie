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
import com.example.ndrly.myapplication.constant.Constant.Companion.API_SORT_QUERY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), MainViewInterface {

    val TAG = "MV_MAIN_ACT"
    var PAGE = 1

    lateinit var adapter: RecyclerView.Adapter<MoviesAdapter.MoviesHolder>
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMVP()
        setupViews()
        getMovies(API_SORT_QUERY, PAGE)
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
        val layoutManager = LinearLayoutManager(this)
        val isLoading = false
        rvMovies?.layoutManager = layoutManager
        rvMovies?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    getMovies(API_SORT_QUERY, PAGE++)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun getMovies(sortQuery: String, page: Int) {
        presenter.getMovies(sortQuery, page)
    }
}
