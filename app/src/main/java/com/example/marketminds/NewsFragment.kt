package com.example.marketminds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketminds.newsfiles.NewsAdapterAll
import com.example.marketminds.newsfiles.NewsModel

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapterAll: NewsAdapterAll
    private val newsList = arrayListOf<NewsModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load the data for the news categories
        loadNewsData()

        // Initialize and set the adapter
        newsAdapterAll = NewsAdapterAll(newsList)
        recyclerView.adapter = newsAdapterAll

        return view
    }

    // Function to load the news categories and their corresponding images
    private fun loadNewsData() {
        newsList.add(NewsModel(R.drawable.business, "Business News"))
        newsList.add(NewsModel(R.drawable.health, "Health News"))
        newsList.add(NewsModel(R.drawable.technology, "Technology News"))
        newsList.add(NewsModel(R.drawable.science, "Science News"))
        newsList.add(NewsModel(R.drawable.sports, "Sports News"))
        newsList.add(NewsModel(R.drawable.entertainment, "Entertainment News"))
    }
}
