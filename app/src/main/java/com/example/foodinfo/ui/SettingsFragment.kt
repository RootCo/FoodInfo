package com.example.foodinfo.ui

import androidx.fragment.app.viewModels
import com.example.foodinfo.databinding.FragmentSettingsBinding
import com.example.foodinfo.utils.appComponent
import com.example.foodinfo.view_model.SettingsViewModel


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {

    private val viewModel: SettingsViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }
}