package com.example.foodinfo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R

class SearchInputAdapter(
    context: Context,
    private val onArrowClickListener: (String) -> Unit,
    private val onItemClickListener: (String) -> Unit
) : RecyclerView.Adapter<SearchInputAdapter.SearchViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    private lateinit var data: List<String>
    fun setData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

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
        holder.textView.text = data[position]
        holder.textView.setOnClickListener { onItemClickListener(data[position]) }
        holder.applySearchView.setOnClickListener { onItemClickListener(data[position]) }
        holder.applyTextView.setOnClickListener { onArrowClickListener(data[position]) }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}