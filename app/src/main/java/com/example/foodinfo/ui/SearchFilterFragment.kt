package com.example.foodinfo.ui

import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodinfo.R
import com.example.foodinfo.databinding.FragmentSearchFilterBinding
import com.example.foodinfo.utils.applicationComponent
import com.example.foodinfo.view_model.SearchFilterViewModel

class SearchFilterFragment : BaseFragment<FragmentSearchFilterBinding>(
    FragmentSearchFilterBinding::inflate
) {

    private val viewModel: SearchFilterViewModel by viewModels {
        activity!!.applicationComponent.viewModelsFactory()
    }

    private val onBackClickListener: () -> Unit = {
        findNavController().navigateUp()
    }


    override fun initUI() {
        val buttonBack: ImageView

        with(binding.root) {
            buttonBack = findViewById(R.id.btn_back)
        }

        buttonBack.setOnClickListener { onBackClickListener() }
    }

    override fun releaseUI() {

    }
}