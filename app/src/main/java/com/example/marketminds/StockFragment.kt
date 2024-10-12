package com.example.marketminds

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketminds.stocks.StockAdapter
import com.example.marketminds.stocks.StockApi
import com.example.marketminds.stocks.StockModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StockFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var stockAdapter: StockAdapter
    private val stockList = arrayListOf<StockModel>()

    private val stockSymbols = listOf("AAPL", "GOOGL", "MSFT")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stock, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Initialize and set the adapter
        stockAdapter = StockAdapter(stockList)
        recyclerView.adapter = stockAdapter

        loadStockData()
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadStockData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.alphavantage.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(StockApi::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            stockList.clear()

            for (symbol in stockSymbols) {
                try {
                    val response = service.getStockData(
                        function = "GLOBAL_QUOTE",
                        symbol = symbol,
                        apiKey = "API KEY"  // Replace with your actual API key
                    )

                    if (response.isSuccessful) {
                        val stockResponse = response.body()

                        stockResponse?.globalQuote?.let { globalQuote ->
                            val stockName = globalQuote.symbol
                            val stockPrice = globalQuote.price
                            val percentageChange = globalQuote.changePercentage

                            stockList.add(StockModel(stockName, stockPrice, percentageChange))
                        } ?: run {
                            Log.e("StockFragment", "globalQuote is null for $symbol")
                        }
                    } else {
                        Log.e("StockFragment", "API call unsuccessful for $symbol: ${response.errorBody()?.string()}")
                    }
                } catch (e: Exception) {
                    Log.e("StockFragment", "Error fetching stock data for $symbol", e)
                }
            }

            // Notify the adapter on the main thread after all stocks are added
            withContext(Dispatchers.Main) {
                stockAdapter.notifyDataSetChanged()
            }
        }
    }
}
