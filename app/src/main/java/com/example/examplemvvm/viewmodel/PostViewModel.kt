package com.example.examplemvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.model.Post
import com.example.examplemvvm.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    private var _postAll = MutableStateFlow<List<Post>>(mutableListOf())
    var postAll = _postAll.asStateFlow()


    fun getPosts() {
        viewModelScope.launch {
            _postAll.value = postRepository.getPost()
        }
    }
}