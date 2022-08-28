package com.example.foodinfo.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.foodinfo.databinding.FragmentFavoriteBinding
import com.example.foodinfo.ui.adapter.FavoriteAdapter
import com.example.foodinfo.ui.decorator.FavoriteItemDecoration
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.FavoriteViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {

    private val viewModel: FavoriteViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private lateinit var recyclerAdapter: FavoriteAdapter

    private val onEditClickListener: () -> Unit = {
        viewModel.setEditMode(true)
    }

    private val onDeleteClickListener: () -> Unit = {
        viewModel.delSelected()
        viewModel.unselectAll()
        viewModel.setEditMode(false)
    }

    private val onCancelClickListener: () -> Unit = {
        viewModel.unselectAll()
        viewModel.setEditMode(false)
        recyclerAdapter.notifyDataSetChanged()
    }

    private val isSelected: (String) -> Boolean = { id ->
        viewModel.isSelected(id)
    }

    private val isEditMode: () -> Boolean = {
        viewModel.isEditMode.value
    }


    private val onReadyToSelect: (String) -> Unit = { id ->
        viewModel.updateSelectStatus(id)
    }

    private val onReadyToNavigate: (String) -> Unit = { id ->
        findNavController().navigate(
            FavoriteFragmentDirections.actionFFavoriteToFRecipeExtended(id)
        )
    }


    override fun initUI() {
        viewModel.updateSelected()

        with(binding) {
            btnEdit.setOnClickListener { onEditClickListener() }
            btnDelete.setOnClickListener { onDeleteClickListener() }
            btnCancel.setOnClickListener { onCancelClickListener() }
        }

        recyclerAdapter = FavoriteAdapter(
            requireContext(),
            isEditMode,
            isSelected,
            onReadyToSelect,
            onReadyToNavigate
        )

        with(binding.rvRecipes) {
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addItemDecoration(
                FavoriteItemDecoration(
                    resources.getDimensionPixelSize(com.example.foodinfo.R.dimen.favorite_item_space),
                )
            )
        }
    }

    override fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recipes.collectLatest(recyclerAdapter::submitData)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isEditMode.collectLatest { isSelected ->
                    binding.llBaseMenu.isVisible = !isSelected
                    binding.llEditMenu.isVisible = isSelected
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedCount.collectLatest { count ->
                    binding.tvSelectedCount.text = count.toString()
                }
            }
        }
    }
}