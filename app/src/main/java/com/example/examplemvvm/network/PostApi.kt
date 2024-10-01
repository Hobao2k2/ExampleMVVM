package com.example.examplemvvm.network

import com.example.examplemvvm.model.Post
import retrofit2.http.GET

interface PostApi {
    @GET("posts")
    suspend fun getPosts():List<Post>
}