package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchFilterNutrientsBinding
import com.example.foodinfo.ui.adapter.FilterNutrientFieldEditAdapter
import com.example.foodinfo.ui.decorator.ListVerticalItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.utils.repeatOn
import com.example.foodinfo.utils.showDescriptionDialog
import com.example.foodinfo.view_model.SearchFilterNutrientsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFilterNutrientsFragment : BaseFragment<FragmentSearchFilterNutrientsBinding>(
    FragmentSearchFilterNutrientsBinding::inflate
) {

    private val viewModel: SearchFilterNutrientsViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val args: SearchFilterNutrientsFragmentArgs by navArgs()

    private lateinit var recyclerAdapter: FilterNutrientFieldEditAdapter

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }

    private val onResetClickListener: () -> Unit = {
        viewModel.reset()
    }

    private val onHeaderClickCallback: (String) -> Unit = { fieldName ->
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val labelItem = viewModel.getNutrient(fieldName)
            withContext(Dispatchers.Main) {
                showDescriptionDialog(
                    labelItem.label,
                    labelItem.description,
                    labelItem.preview
                )
            }
        }
    }

    private val onValueChangedCallback: (Long, Float, Float) -> Unit = { id, minValue, maxValue ->
        viewModel.updateField(id, minValue, maxValue)
    }


    override fun initUI() {
        recyclerAdapter = FilterNutrientFieldEditAdapter(
            requireContext(),
            onHeaderClickCallback,
            onValueChangedCallback
        )

        binding.btnBack.setOnClickListener { onBackClickListener() }
        binding.btnReset.setOnClickListener { onResetClickListener() }

        with(binding.rvNutrients) {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            itemAnimator = null
            addItemDecoration(
                ListVerticalItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.filter_nutrients_edit_field_item_space),
                    resources.getDimensionPixelSize(R.dimen.filter_nutrients_edit_field_item_margin),
                )

            )
        }
    }

    override fun subscribeUI() {
        super.subscribeUI()
        repeatOn(Lifecycle.State.STARTED) {
            viewModel.nutrients.collectLatest(recyclerAdapter::submitList)
        }
    }
}
