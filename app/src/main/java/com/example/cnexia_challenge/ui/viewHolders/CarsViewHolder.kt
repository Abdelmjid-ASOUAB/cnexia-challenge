package com.example.cnexia_challenge.ui.viewHolders

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cnexia_challenge.R
import com.example.cnexia_challenge.databinding.CarItemBinding
import com.example.cnexia_challenge.models.Car
import com.example.cnexia_challenge.ui.adapters.ProsConsAdapter

class CarsViewHolder(private val binding: CarItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    private var prosAdapter: ProsConsAdapter = ProsConsAdapter()
    private var consAdapter: ProsConsAdapter = ProsConsAdapter()

    /**
     * Bind the car data to the view
     */
    fun bind(car: Car) {
        this.binding.carName.text = car.make
        this.binding.carPrice.text = this.context.getString(
            R.string.price, car.customerPrice.toString()
        )
        this.binding.rating.rating = car.rating.toFloat()
        setImageDrawable(car)
        initProsRecyclerView(car.prosList)
        initConsRecyclerView(car.consList)
    }

    /**
     * Initialize the pros recycler view
     */
    private fun initProsRecyclerView(prosList: List<String>) {
        this.binding.listPros.setHasFixedSize(true)
        this.binding.listPros.layoutManager = LinearLayoutManager(this.context)
        this.binding.listPros.adapter = prosAdapter
        this.prosAdapter.setValueList(prosList)
    }

    /**
     * Initialize the cons recycler view
     */
    private fun initConsRecyclerView(consList: List<String>) {
        this.binding.listCons.setHasFixedSize(true)
        this.binding.listCons.layoutManager = LinearLayoutManager(this.context)
        this.binding.listCons.adapter = consAdapter
        this.consAdapter.setValueList(consList)
    }

    /**
     * Set the image drawable based on the car make
     */
    private fun setImageDrawable(car: Car) {
        val image = if (car.make.lowercase().contains("mercedes")) {
            R.drawable.mercedez
        } else if (car.make.lowercase().contains("bmw")) {
            R.drawable.bmw
        } else if (car.make.lowercase().contains("alpine")) {
            R.drawable.alpine_roadster
        } else if (car.make.lowercase().contains("land")) {
            R.drawable.range_rover
        } else {
            R.drawable.tacoma
        }

        this.binding.carImage.setImageResource(image)
    }

    /**
     * Set the click listener
     */
    fun setClickListener(position: Int, onClickListener: (Int) -> Unit) {
        this.binding.root.setOnClickListener {
            onClickListener(position)
        }
    }

    /**
     * Hide the line if it is the last item
     */
    fun isLastItem(lastItem: Boolean) {
        if (lastItem) {
            this.binding.cardLine.visibility = View.GONE
        }
    }

    fun selectedItem(bool: Boolean) {
        if (bool) {
            this.binding.details.visibility = View.VISIBLE
        } else {
            this.binding.details.visibility = View.GONE
        }
    }
}