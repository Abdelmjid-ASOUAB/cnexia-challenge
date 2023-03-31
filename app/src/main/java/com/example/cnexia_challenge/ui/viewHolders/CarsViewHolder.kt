package com.example.cnexia_challenge.ui.viewHolders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cnexia_challenge.R
import com.example.cnexia_challenge.databinding.CarItemBinding
import com.example.cnexia_challenge.models.Car

class CarsViewHolder(private val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car, context : Context) {
        binding.carName.text = car.make
        binding.carPrice.text = context.getString(
            R.string.price,
            car.customerPrice.toString()
        )
        binding.rating.rating = car.rating.toFloat()
    }


    // is Last Item
    fun isLastItem(lastItem: Boolean) {
        if (lastItem) {
            binding.cardLine.visibility = View.GONE
        }
    }
}