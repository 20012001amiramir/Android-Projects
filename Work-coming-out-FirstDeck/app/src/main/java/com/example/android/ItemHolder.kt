package com.example.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_sport.*

class ItemHolder(
    override val containerView: View,
    private val clickLambda: (String, String, Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(sport: Sport) {
        iv_city.setImageResource(sport.image)
        itemView.setOnClickListener {
            clickLambda(sport.name, sport.desc, sport.image)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (String, String, Int) -> Unit) =
            ItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_sport, parent, false),
                clickLambda
            )
    }
}
