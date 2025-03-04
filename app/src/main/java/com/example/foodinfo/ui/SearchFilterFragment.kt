package com.example.foodinfo.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchFilterBinding
import com.example.foodinfo.ui.adapter.FilterBaseFieldAdapter
import com.example.foodinfo.ui.adapter.FilterCategoriesAdapter
import com.example.foodinfo.ui.adapter.FilterNutrientsAdapter
import com.example.foodinfo.ui.custom_view.NonScrollLinearLayoutManager
import com.example.foodinfo.ui.decorator.ListItemDecoration
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

    private val onResetClickListener: () -> Unit = {
        viewModel.reset()
    }

    private val onNutrientsEditClickListener: () -> Unit = {
        findNavController().navigate(
            SearchFilterFragmentDirections.actionFSearchFilterToFSearchFilterNutrients(
                viewModel.filterName
            )
        )
    }

    private val onBaseFieldValueChangedCallback: (Int, Float, Float) -> Unit = { id, minValue, maxValue ->
        viewModel.updateField(id, minValue, maxValue)
    }

    private val onCategoryChangedCallback: (Int) -> Unit = { categoryID ->
        findNavController().navigate(
            SearchFilterFragmentDirections.actionFSearchFilterToFSearchFilterCategory(
                categoryID,
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
        binding.btnReset.setOnClickListener { onResetClickListener() }
        binding.ivNutrientsEdit.setOnClickListener {
            onNutrientsEditClickListener()
        }

        recyclerAdapterBaseFields = FilterBaseFieldAdapter(
            requireContext(),
            onBaseFieldValueChangedCallback
        )
        with(binding.rvBaseFields) {
            adapter = recyclerAdapterBaseFields
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            itemAnimator = null
            addItemDecoration(
                ListItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_base_range_field_item_space),
                    RecyclerView.VERTICAL
                )

            )
        }

        recyclerAdapterCategories = FilterCategoriesAdapter(
            requireContext(),
            onCategoryChangedCallback
        )
        with(binding.rvCategories) {
            adapter = recyclerAdapterCategories
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            itemAnimator = null
            addItemDecoration(
                ListItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_category_item_space),
                    RecyclerView.VERTICAL
                )
            )
        }

        recyclerAdapterNutrients = FilterNutrientsAdapter(
            requireContext(),
            getFormattedRange
        )
        with(binding.rvNutrients) {
            adapter = recyclerAdapterNutrients
            layoutManager = NonScrollLinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            itemAnimator = null
            addItemDecoration(
                ListItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_category_item_space),
                    RecyclerView.VERTICAL
                )
            )
        }
    }

    override fun subscribeUI() {
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.filter.collectLatest { filter ->
                recyclerAdapterCategories.submitList(filter.categories)
                recyclerAdapterBaseFields.submitList(filter.baseFields)
                if (filter.nutrients.isEmpty()) {
                    binding.rvNutrients.isVisible = false
                    binding.tvNutrientsNoData.isVisible = true
                } else {
                    binding.rvNutrients.isVisible = true
                    binding.tvNutrientsNoData.isVisible = false
                    recyclerAdapterNutrients.submitList(filter.nutrients)
                }
            }
        }
    }
}