package com.example.cnexia_challenge.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cnexia_challenge.databinding.CarItemBinding
import com.example.cnexia_challenge.models.Car
import com.example.cnexia_challenge.ui.viewHolders.CarsViewHolder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CarsAdapter @Inject constructor(
    @ApplicationContext val context: Context
) : RecyclerView.Adapter<CarsViewHolder>() {

    private var carsList: List<Car> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bind(carsList[position], context)
        holder.isLastItem(position == carsList.size - 1)
    }

    override fun getItemCount(): Int = carsList.size

    fun setCarsList(carsList: List<Car>) {
        this.carsList = carsList
        notifyDataSetChanged()
    }
}
