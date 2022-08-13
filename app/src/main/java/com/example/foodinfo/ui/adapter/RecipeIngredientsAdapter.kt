package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.databinding.RvItemIngredientBinding
import com.example.foodinfo.repository.model.RecipeIngredientModel
import com.example.foodinfo.ui.view_holder.IngredientsViewHolder


class RecipeIngredientsAdapter(
    context: Context
) : ListAdapter<RecipeIngredientModel, ViewHolder>(
    RecipeIngredientModel.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return IngredientsViewHolder(
            RvItemIngredientBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { recipe ->
            holder as IngredientsViewHolder
            holder.bind(recipe)
        }
    }
}