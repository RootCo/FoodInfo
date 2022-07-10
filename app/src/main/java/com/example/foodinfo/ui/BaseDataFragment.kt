package com.example.foodinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


abstract class BaseDataFragment<VB : ViewBinding>(
    bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(bindingInflater) {

    abstract fun updateViewModelData()

    // чтобы обновлять после resume, но не обновлять дважды после create
    private var requireUpdates = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        updateViewModelData()
        requireUpdates = false
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (requireUpdates) updateViewModelData()
        requireUpdates = true
    }
}