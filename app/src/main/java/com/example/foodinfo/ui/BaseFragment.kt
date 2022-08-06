package com.example.foodinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base class to avoid boilerplate binding initialization and releasing.
 */
abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    /**
     * Calls in onViewCreated().
     *
     * Function to initialize Views  add clickListeners to them.
     */
    open fun initUI() {}

    /**
     * Calls in onResume().
     *
     * Function to launch all coroutines and subscribe all necessary Views to ViewModel data
     */
    open fun subscribeUI() {}

    /**
     * Calls in onStop().
     *
     * Function to cancel all unnecessary coroutines
     */
    open fun unsubscribeUI() {}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        subscribeUI()
    }

    override fun onStop() {
        super.onStop()
        unsubscribeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}