package com.example.cnexia_challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cnexia_challenge.databinding.CarItemBinding
import com.example.cnexia_challenge.models.Car
import com.example.cnexia_challenge.ui.viewHolders.CarsViewHolder
import javax.inject.Inject

class CarsAdapter @Inject constructor() : RecyclerView.Adapter<CarsViewHolder>() {

    /**
     * List of cars to be displayed
     */
    private var carsList: List<Car> = emptyList()

    /**
     * Selected item in the list
     * default value is 0
     */
    private var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding = CarItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CarsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val car = carsList[position]

        holder.bind(car)
        holder.isLastItem(position == carsList.size - 1)
        holder.selectedItem(selectedItem == position)

        holder.setClickListener(position) {
            selectedItem = it
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = carsList.size

    /**
     * Set the list of cars to be displayed
     */
    fun setCarsList(carsList: List<Car>) {
        this.carsList = carsList
        notifyDataSetChanged()
    }
}
