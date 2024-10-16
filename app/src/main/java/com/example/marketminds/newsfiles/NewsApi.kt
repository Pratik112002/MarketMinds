package com.example.marketminds.newsfiles

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String?,
        @Query("pageSize") pageSize:Int,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}
