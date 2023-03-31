package com.example.cnexia_challenge.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.cnexia_challenge.databinding.ProConsItemBinding

class ProsConsViewHolder(private val binding: ProConsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind the car data to the view
     */
    fun bind(value: String) {
        this.binding.value.text = value
    }
}
