package com.example.marketminds.newsfiles

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketminds.NewsFragment
import com.example.marketminds.R
import com.example.marketminds.newscategory.BusinessNewsActivity
import com.example.marketminds.newscategory.EntertainmentNews
import com.example.marketminds.newscategory.HealthNews
import com.example.marketminds.newscategory.ScienceNews
import com.example.marketminds.newscategory.SportsNews
import com.example.marketminds.newscategory.TechnologyNews

class NewsAdapterAll(val newsList: ArrayList<NewsModel>) : RecyclerView.Adapter<NewsAdapterAll.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImg: ImageView = itemView.findViewById(R.id.image_card)
        var newsTitle: TextView = itemView.findViewById(R.id.news_category)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val news = newsList[position]


                // Navigate to the appropriate activity based on the clicked news category
                val intent: Intent = when (news.newsTitle) {
                    "Business News" -> Intent(itemView.context, BusinessNewsActivity::class.java)
                    "Health News" -> Intent(itemView.context, HealthNews::class.java)
                    "Technology News" -> Intent(itemView.context, TechnologyNews::class.java)
                    "Science News" -> Intent(itemView.context, ScienceNews::class.java)
                    "Sports News" -> Intent(itemView.context, SportsNews::class.java)
                    "Entertainment News" -> Intent(itemView.context, EntertainmentNews::class.java)

                    else -> Intent(itemView.context, NewsFragment::class.java)
                }

                // Start the activity
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_items_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.newsImg.setImageResource(newsList[position].newsImg)
        holder.newsTitle.text = newsList[position].newsTitle
    }
}
