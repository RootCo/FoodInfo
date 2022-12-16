package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchFilterBinding
import com.example.foodinfo.ui.adapter.FilterBaseFieldAdapter
import com.example.foodinfo.ui.adapter.FilterCategoriesAdapter
import com.example.foodinfo.ui.adapter.FilterNutrientsAdapter
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

    private lateinit var recyclerAdapterBaseFields: FilterBaseFieldAdapter
    private lateinit var recyclerAdapterCategories: FilterCategoriesAdapter
    private lateinit var recyclerAdapterNutrients: FilterNutrientsAdapter

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onNutrientsEditClickListener: () -> Unit = {
        findNavController().navigate(
            SearchFilterFragmentDirections.actionFSearchFilterToFSearchFilterNutrients(
                viewModel.filterName
            )
        )
    }

    private val onBaseFieldValueChangedCallback: (Float, Float) -> Unit = { minValue, maxValue ->

    }

    private val onCategoryChangedCallback: (String) -> Unit = { category ->
        findNavController().navigate(
            SearchFilterFragmentDirections.actionFSearchFilterToFSearchFilterCategory(
                category,
                viewModel.filterName
            )
        )
    }

    private val getFormattedRange: (Float, Float, String) -> String =
        { minValue, maxValue, measure ->
            getString(
                R.string.rv_item_filter_nutrient_range,
                minValue,
                maxValue,
                measure
            )
        }


    override fun initUI() {
        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.ivNutrientsEdit.setOnClickListener {
            onNutrientsEditClickListener()
        }

        recyclerAdapterBaseFields = FilterBaseFieldAdapter(
            requireContext(),
            onBaseFieldValueChangedCallback
        )
        with(binding.llBaseFields) {
            adapter = recyclerAdapterBaseFields
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            itemAnimator = null
            addItemDecoration(
                ListVerticalItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_base_range_field_item_space),
                )

            )
        }

        recyclerAdapterCategories = FilterCategoriesAdapter(
            requireContext(),
            onCategoryChangedCallback
        )
        with(binding.llCategories) {
            adapter = recyclerAdapterCategories
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            itemAnimator = null
            addItemDecoration(
                ListVerticalItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_category_item_space)
                )
            )
        }

        recyclerAdapterNutrients = FilterNutrientsAdapter(
            requireContext(),
            getFormattedRange
        )
        with(binding.llNutrients) {
            adapter = recyclerAdapterNutrients
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            itemAnimator = null
            addItemDecoration(
                ListVerticalItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_category_item_space)
                )
            )
        }
    }

    override fun subscribeUI() {
        super.subscribeUI()
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.rangeFields.collectLatest(recyclerAdapterBaseFields::submitList)
        }
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.categories.collectLatest(recyclerAdapterCategories::submitList)
        }
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.nutrients.collectLatest(recyclerAdapterNutrients::submitList)
        }
    }
}