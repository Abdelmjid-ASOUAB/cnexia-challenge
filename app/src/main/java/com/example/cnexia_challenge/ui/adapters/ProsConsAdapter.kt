package com.example.cnexia_challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cnexia_challenge.databinding.ProConsItemBinding
import com.example.cnexia_challenge.ui.viewHolders.ProsConsViewHolder
import javax.inject.Inject

class ProsConsAdapter @Inject constructor() : RecyclerView.Adapter<ProsConsViewHolder>() {

    private var valueList: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProsConsViewHolder {
        val binding = ProConsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProsConsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProsConsViewHolder, position: Int) {
        holder.bind(this.valueList[position])
    }

    override fun getItemCount(): Int = this.valueList.size

    /**
     * Set the list of pros or cons to be displayed
     */
    fun setValueList(valueList: List<String>) {
        this.valueList = valueList
        notifyDataSetChanged()
    }
}
