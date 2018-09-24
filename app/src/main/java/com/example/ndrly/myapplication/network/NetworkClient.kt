package com.example.ndrly.myapplication.network

import com.example.ndrly.myapplication.constant.Constant.Companion.API_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    companion object {
        fun getRetrofit() : Retrofit {
            var retrofit: Retrofit? = null
            if (retrofit == null) {
                val builder = OkHttpClient.Builder()
                val okHttpClient: OkHttpClient = builder.build()

                retrofit = Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build()
            }
            return retrofit!!;
        }
    }
}