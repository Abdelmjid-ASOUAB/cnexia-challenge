package com.example.cnexia_challenge.ui.viewHolders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cnexia_challenge.R
import com.example.cnexia_challenge.databinding.CarItemBinding
import com.example.cnexia_challenge.models.Car

class CarsViewHolder(private val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Bind the car data to the view
     */
    fun bind(car: Car, context: Context) {
        this.binding.carName.text = car.make
        this.binding.carPrice.text = context.getString(
            R.string.price, car.customerPrice.toString()
        )
        this.binding.rating.rating = car.rating.toFloat()
        setImageDrawable(car)
    }

    /**
     * Set the image drawable based on the car make
     */
    private fun setImageDrawable(car: Car) {
        val image = if (car.make.lowercase().contains("mercedes")) {
            R.drawable.mercedez
        } else if (car.make.lowercase().contains("bmw")) {
            R.drawable.bmw
        } else if (car.make.lowercase().contains("alpina")) {
            R.drawable.alpine_roadster
        } else if (car.make.lowercase().contains("land")) {
            R.drawable.range_rover
        } else {
            R.drawable.tacoma
        }

        this.binding.carImage.setImageResource(image)
    }

    /**
     * Hide the line if it is the last item
     */
    fun isLastItem(lastItem: Boolean) {
        if (lastItem) {
            this.binding.cardLine.visibility = View.GONE
        }
    }
}