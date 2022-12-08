package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchFilterBinding
import com.example.foodinfo.ui.adapter.FilterBaseFieldAdapter
import com.example.foodinfo.ui.custom_view.NonScrollLinearLayoutManager
import com.example.foodinfo.ui.decorator.ListVerticalItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.repeatOn
import com.example.foodinfo.view_model.SearchFilterViewModel
import kotlinx.coroutines.flow.collectLatest


class SearchFilterFragment : BaseFragment<FragmentSearchFilterBinding>(
    FragmentSearchFilterBinding::inflate
) {

    private val viewModel: SearchFilterViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: FilterBaseFieldAdapter

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onValueChangedCallback: (Float, Float, Boolean) -> Unit =
        { minValue, maxValue, isDefault ->
            if (isDefault) {
                //TODO
            } else {
                //TODO
            }
        }


    override fun initUI() {
        recyclerAdapter = FilterBaseFieldAdapter(
            requireContext(),
            onValueChangedCallback
        )

        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.tvNutrientsHeader.setOnClickListener {
            recyclerAdapter.notifyDataSetChanged()
        }

        with(binding.llBaseFields) {
            adapter = recyclerAdapter
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
                it.isScrollEnabled = false
            }
            setHasFixedSize(true)
            itemAnimator = null
            addItemDecoration(
                ListVerticalItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_base_range_field_item_space),
                )

            )
        }
    }

    override fun subscribeUI() {
        super.subscribeUI()
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.rangeFields.collectLatest(recyclerAdapter::submitList)
        }
    }
}