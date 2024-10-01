package com.example.examplemvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examplemvvm.R
import com.example.examplemvvm.model.Post

class PostAdapter(val listPost: MutableList<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId = itemView.findViewById<TextView>(R.id.userId)
        val title = itemView.findViewById<TextView>(R.id.Title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listPost[position]
        holder.userId.text = post.userId.toString()
        holder.title.text = post.title
    }

    override fun getItemCount(): Int {
        return listPost.size
    }
}


