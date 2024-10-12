package com.example.marketminds.newscategory

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketminds.newsfiles.NewsAdapter
import com.example.marketminds.newsfiles.NewsApiService
import com.example.marketminds.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EntertainmentNews : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter // Assuming you have created an adapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entertainment_news) // Update this with your activity's layout

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the adapter
        newsAdapter = NewsAdapter(emptyList()) // Initially empty
        recyclerView.adapter = newsAdapter

        // Fetch news articles
        fetchNews()
    }

    private fun fetchNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTopHeadlines(category = "entertainment", pageSize = 100,apiKey = "977b51ac6dfd4e93a2709f65b3b6ddc6") // Replace with your API key

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse ->
                        newsAdapter.updateArticles(newsResponse.articles)
                    }
                } else {
                    Toast.makeText(this@EntertainmentNews, "Failed to load news", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
