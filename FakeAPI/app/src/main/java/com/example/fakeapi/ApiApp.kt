package com.example.fakeapi

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ApiApp : Application() {
    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

    }

    companion object {
        lateinit var instance: ApiApp
            private set
    }
}
