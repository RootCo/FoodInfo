package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.databinding.RvItemFilterInputNutrientsEditBinding
import com.example.foodinfo.repository.model.NutrientFilterEditModel
import com.example.foodinfo.repository.model.RangeFieldModel
import com.example.foodinfo.ui.view_holder.FilterNutrientFieldEditViewHolder


class FilterNutrientFieldEditAdapter(
    context: Context,
    private val onHeaderClickCallback: (String) -> Unit,
    private val onValueChangedCallback: (Float, Float, Boolean) -> Unit
) : ListAdapter<NutrientFilterEditModel, ViewHolder>(NutrientFilterEditModel.ItemCallBack) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return FilterNutrientFieldEditViewHolder(
            RvItemFilterInputNutrientsEditBinding.inflate(layoutInflater, parent, false),
            onHeaderClickCallback,
            onValueChangedCallback
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { field ->
            holder as FilterNutrientFieldEditViewHolder
            holder.bind(field)
        }
    }
}