package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.databinding.RvItemFilterInputCategoryEditBinding
import com.example.foodinfo.repository.model.LabelFilterEditModel
import com.example.foodinfo.ui.view_holder.FilterCategoryEditViewHolder


class FilterCategoryEditAdapter(
    context: Context,
    private val onQuestionMarkClickListener: (String) -> Unit,
    private val onItemClickListener: (Long, Boolean) -> Unit,
) : ListAdapter<LabelFilterEditModel, ViewHolder>(
    LabelFilterEditModel.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return FilterCategoryEditViewHolder(
            RvItemFilterInputCategoryEditBinding.inflate(layoutInflater, parent, false),
            onQuestionMarkClickListener,
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { label ->
            holder as FilterCategoryEditViewHolder
            holder.bind(label)
        }
    }
}