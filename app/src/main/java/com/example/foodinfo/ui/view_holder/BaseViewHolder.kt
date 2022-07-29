package com.example.foodinfo.ui.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Base ViewHolder class that allows to set clickListeners once during onCreateViewHolder
 * and don't doing that every time at onBindViewHolder.
 */
abstract class BaseViewHolder<I : Any>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    lateinit var item: I

    open fun bind(item: I) {
        this.item = item
    }
}