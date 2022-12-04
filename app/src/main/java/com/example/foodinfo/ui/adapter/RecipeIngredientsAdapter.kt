package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodinfo.databinding.RvItemRecipeIngredientBinding
import com.example.foodinfo.repository.model.RecipeIngredientModel
import com.example.foodinfo.ui.view_holder.IngredientsViewHolder


class RecipeIngredientsAdapter(
    context: Context,
    private val onGetWeight: (Double) -> String,
    private val onGetQuantity: (Double, String) -> String,
) : ListAdapter<RecipeIngredientModel, ViewHolder>(
    RecipeIngredientModel.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return IngredientsViewHolder(
            RvItemRecipeIngredientBinding.inflate(layoutInflater, parent, false),
            onGetWeight,
            onGetQuantity
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { ingredient ->
            holder as IngredientsViewHolder
            holder.bind(ingredient)
        }
    }
}