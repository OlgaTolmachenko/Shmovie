package com.example.ndrly.myapplication.ui

import android.util.Log
import com.example.ndrly.myapplication.beans.Response
import com.example.ndrly.myapplication.constant.Constant.Companion.API_KEY
import com.example.ndrly.myapplication.constant.Constant.Companion.API_SORT_QUERY
import com.example.ndrly.myapplication.constant.Constant.Companion.ERROR_FETCHING_DATA
import com.example.ndrly.myapplication.network.NetworkClient
import com.example.ndrly.myapplication.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MainPresenter(val view: MainViewInterface): MainPresenterInterface {

    val TAG: String = "MV_MAIN_PRES"

    override fun getMovies(sortQuery: String, page: Int) {
        getObservable(sortQuery, page).subscribeWith(getObserver())
    }

    fun getObservable(sortQuery: String, page: Int): Observable<Response> {
        return NetworkClient.getRetrofit().create(NetworkInterface::class.java)
                .getMovies(sortQuery, API_KEY, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getObserver(): DisposableObserver<Response> {
        return object : DisposableObserver<Response>() {

            override fun onNext(response: Response) {
                view.displayMovies(response)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                view.displayError(ERROR_FETCHING_DATA)
            }

            override fun onComplete() {
                Log.d(TAG, "Completed loading movie data")
            }
        }
    }
}