package com.example.foodinfo.ui.view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.foodinfo.R
import com.example.foodinfo.model.local.CategoryItem
import com.google.android.material.imageview.ShapeableImageView


class ExploreViewHolder(
    itemView: View,
    onItemClickListener: (String, String) -> Unit
) : BaseViewHolder<CategoryItem>(itemView) {

    private val title: TextView = itemView.findViewById(
        R.id.tv_title
    )
    private val preview: ShapeableImageView = itemView.findViewById(
        R.id.ic_preview
    )
    private val background: ConstraintLayout = itemView.findViewById(
        R.id.cl_background
    )


    init {
        background.setOnClickListener {
            onItemClickListener(item.category, item.label)
        }
    }


    override fun bind(item: CategoryItem) {
        super.bind(item)
        with(this.item) {
            title.text = label
            preview.setImageDrawable(icon)
        }
    }

    companion object {
        fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
            return layoutInflater.inflate(
                R.layout.rv_item_explore, parent, false
            )
        }
    }
}
