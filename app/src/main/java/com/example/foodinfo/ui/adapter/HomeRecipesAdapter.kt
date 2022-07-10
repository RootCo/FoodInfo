package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodinfo.R
import com.example.foodinfo.model.entities.RecipeShort
import com.example.foodinfo.utils.Utils
import com.google.android.material.imageview.ShapeableImageView


class HomeRecipesAdapter(
    context: Context,
    private val utils: Utils,
    private val onItemClickListener: (String) -> Unit,
) : ListAdapter<RecipeShort, HomeRecipesAdapter.HomeViewHolder>(
    RecipeShort.ItemCallBack
) {

    private val layoutInflater = LayoutInflater.from(context)


    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val caloriesView: TextView = itemView.findViewById(R.id.tv_home_recipe_calories)
        val nameView: TextView = itemView.findViewById(R.id.tv_home_recipe_name)
        val imageView: ShapeableImageView =
            itemView.findViewById(R.id.iv_home_recipe_preview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): HomeViewHolder {
        val itemView = layoutInflater.inflate(
            R.layout.home_rv_item, parent, false
        )

        /*
        задаем размеры элемента
        ширину высчитываем в рантайме, т.к. по другому не придумал
        для доп информации см HomeItemDecoration и getHomeRecipeWidth() в Utils
        */
        itemView.layoutParams = ViewGroup.LayoutParams(
            (utils.getHomeRecipeWidth()), ViewGroup.LayoutParams.MATCH_PARENT
        )
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: HomeViewHolder, position: Int
    ) {
        holder.caloriesView.text = getItem(position).calories.toString()
        holder.nameView.text = getItem(position).name
        Glide.with(holder.imageView.context).load(getItem(position).preview)
            .into(holder.imageView)
        holder.imageView.setOnClickListener { onItemClickListener(getItem(position).id) }
    }
}