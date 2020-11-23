package com.example.posts

import android.app.Application
import com.example.posts.api.JsonPlaceHolderApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.realm.Realm
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ApiApp : Application() {
    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi
        private set

    @ExperimentalSerializationApi
    override fun onCreate() {
        super.onCreate()
        instance = this
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        Realm.init(this)
    }

    companion object {
        lateinit var instance: ApiApp
            private set
    }
}
