package com.example.foodinfo.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchTargetBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.SearchTargetViewModel

class SearchTargetFragment : BaseDataFragment<FragmentSearchTargetBinding>(
    FragmentSearchTargetBinding::inflate
) {

    private val args: SearchTargetFragmentArgs by navArgs()


    private val viewModel: SearchTargetViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    override fun updateViewModelData() {
        viewModel.updateRecipes(args.category, args.label)
    }

    override fun initUI() {
        binding.root.findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            findNavController().navigateUp()
        }

        binding.root.findViewById<ImageView>(R.id.btn_search).setOnClickListener {
            val action =
                SearchTargetFragmentDirections.actionFSearchTargetToFSearchInput()
            findNavController().navigate(action)
        }

        binding.root.findViewById<TextView>(R.id.tv_header).text = args.label
    }
}