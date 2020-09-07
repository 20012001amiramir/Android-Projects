package com.example.android

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(
        private var dataSource: List<Sport>,
        private val clickLambda: (String, String, Int) -> Unit
) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder.create(parent, clickLambda)

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    override fun getItemCount() = dataSource.size
}
