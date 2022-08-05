package com.example.foodinfo.ui

import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentExploreBinding
import com.example.foodinfo.ui.adapter.ExploreOuterAdapter
import com.example.foodinfo.ui.decorator.ExploreOuterItemDecoration
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.ExploreViewModel


class ExploreFragment : BaseDataFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
) {

    private val viewModel: ExploreViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
    }

    override fun initUI() {
        val recyclerAdapter = ExploreOuterAdapter(
            context!!,
            onInnerItemClickListener
        )

        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.rv_explore_outer)
        val progressBar = binding.root.findViewById<ProgressBar>(R.id.explore_progress)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(
                ExploreOuterItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.explore_item_outer_space),
                    resources.getDimensionPixelSize(R.dimen.explore_item_outer_margin)
                )
            )
            adapter = recyclerAdapter
        }

        recyclerAdapter.submitList(viewModel.categories)

        binding.root.findViewById<TextView>(R.id.tv_search).setOnClickListener {
            findNavController().navigate(
                ExploreFragmentDirections.actionFExploreToFSearchInput()
            )
        }
    }

    private val onInnerItemClickListener: (String, String) -> Unit = { category, label ->
        findNavController().navigate(
            ExploreFragmentDirections.actionFExploreToFSearchTarget(category, label)
        )
    }
}