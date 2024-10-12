package com.example.marketminds.newsfiles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketminds.R

class NewsAdapter(private var articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    // ViewHolder class to hold the view elements for each item in the RecyclerView
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val newsImageView: ImageView = itemView.findViewById(R.id.imageView)
        val publishedAt: TextView=itemView.findViewById(R.id.publishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]

        // Bind the data to the view elements
        holder.titleTextView.text = article.title
        holder.descriptionTextView.text = article.description
        holder.publishedAt.text=article.publishedAt

        // Load the image using Glide
        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .placeholder(R.drawable.loading) // You can set a placeholder image here
            .into(holder.newsImageView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    // Method to update the list of articles in the adapter
    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
