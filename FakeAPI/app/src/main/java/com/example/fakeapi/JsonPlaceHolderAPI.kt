    package com.example.fakeapi

import retrofit2.Call
import retrofit2.http.*


interface JsonPlaceHolderApi {
    @GET("posts")
    fun fetchAllPosts():Call<List<Post>>

    @POST("posts")
    fun createPost(@Body post:Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int?):Call<Unit>
}