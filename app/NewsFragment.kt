package com.example.marketminds.newsfiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketminds.NewsModel
import com.example.marketminds.R

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapterAll
    private var newsList: ArrayList<NewsModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // Initialize the adapter with the news list
        newsAdapter = NewsAdapterAll(newsList)
        recyclerView.adapter = newsAdapter

        // Load some sample news into the list
        loadNews()
    }

    private fun loadNews() {
        val news1 = NewsModel(R.drawable.business, "Business News")
        val news2 = NewsModel(R.drawable.health, "Health News")
        val news3 = NewsModel(R.drawable.technology, "Technology News")
        val news4 = NewsModel(R.drawable.science, "Science News")
        val news5 = NewsModel(R.drawable.sports, "Sports News")
        val news6 = NewsModel(R.drawable.entertainment, "Entertainment News")

        newsList.add(news1)
        newsList.add(news2)
        newsList.add(news3)
        newsList.add(news4)
        newsList.add(news5)
        newsList.add(news6)

        // Notify adapter about the data change
        newsAdapter.notifyDataSetChanged()
    }
}
