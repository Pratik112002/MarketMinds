package com.example.marketminds.stocks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketminds.R

class StockAdapter(val stockList: ArrayList<StockModel>) : RecyclerView.Adapter<StockAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stock_name: TextView = itemView.findViewById(R.id.txt_stock_name)
        var stock_price: TextView = itemView.findViewById(R.id.txt_stock_price)
        var stock_percentage: TextView = itemView.findViewById(R.id.txt_percentage_change)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stocks, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.stock_name.text=stockList[position].stockName
        holder.stock_price.text = stockList[position].stockPrice
        holder.stock_percentage.text = stockList[position].stockPercentage

    }
}
