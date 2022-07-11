package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.model.entities.SearchInput

class SearchInputAdapter(
    context: Context,
    private val onArrowClickListener: (String) -> Unit,
    private val onItemClickListener: (String) -> Unit
) : ListAdapter<SearchInput, SearchInputAdapter.SearchViewHolder>(
    AsyncDifferConfig.Builder(SearchInput.ItemCallBack).build()
) {

    private val layoutInflater = LayoutInflater.from(context)


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_search_input)
        val applySearchView: ImageView = itemView.findViewById(R.id.iv_apply_search)
        val applyTextView: ImageView = itemView.findViewById(R.id.iv_apply_text)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = layoutInflater.inflate(
            R.layout.search_input_rv_item, parent, false
        )
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.textView.text = getItem(position).inputText
        holder.textView.setOnClickListener { onItemClickListener(getItem(position).inputText) }
        holder.applySearchView.setOnClickListener { onItemClickListener(getItem(position).inputText) }
        holder.applyTextView.setOnClickListener { onArrowClickListener(getItem(position).inputText) }
    }
}