package com.example.posts.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface JsonPlaceHolderApi {
    @GET("posts")
    suspend fun fetchAllPosts(): Response<List<Post>>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int?): Response<Unit>
}