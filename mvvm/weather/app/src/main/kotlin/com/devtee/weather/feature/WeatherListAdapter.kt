package com.devtee.weather.feature

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devtee.weather.R
import com.devtee.weather.databinding.ItemWeatherBinding

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.WeatherCardViewHolder>() {

    var items: List<ForecastItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherCardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class WeatherCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemWeatherBinding = DataBindingUtil.bind(itemView)!!

        fun bind(item: ForecastItem) {
            binding.item = item
        }
    }
}