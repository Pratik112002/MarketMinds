package com.example.marketminds.stocks


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
@GET("query")
suspend fun getStockData(
@Query("function") function:String,
@Query("symbol") symbol:String,
@Query("apikey")apiKey:String
): Response<StockResponse>
}