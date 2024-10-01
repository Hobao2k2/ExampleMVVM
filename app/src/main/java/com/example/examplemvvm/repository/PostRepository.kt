package com.example.examplemvvm.repository

import com.example.examplemvvm.model.Post
import com.example.examplemvvm.network.RetrofitInstance

class PostRepository {
    private val api=RetrofitInstance.getRetrofitClient()

    suspend fun getPost():List<Post>{
        return api.getPosts()
    }
}