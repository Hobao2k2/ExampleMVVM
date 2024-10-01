package com.example.examplemvvm.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examplemvvm.R
import com.example.examplemvvm.adapter.PostAdapter
import com.example.examplemvvm.model.Post
import com.example.examplemvvm.repository.PostRepository
import com.example.examplemvvm.viewmodel.PostViewModel
import com.example.examplemvvm.viewmodel.PostViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var postViewModel: PostViewModel
    private lateinit var adapter: PostAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: MutableList<Post>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        list = mutableListOf()
        adapter = PostAdapter(list)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val postRepository = PostRepository()
        val factory = PostViewModelFactory(postRepository)
        postViewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)
        lifecycleScope.launch {
            postViewModel.getPosts()
            postViewModel.postAll.collect() { posts ->
                list.clear()
                list.addAll(posts.map { Post(it.userId, it.id, it.title, it.body) })
                adapter.notifyDataSetChanged()
            }
        }
    }
}