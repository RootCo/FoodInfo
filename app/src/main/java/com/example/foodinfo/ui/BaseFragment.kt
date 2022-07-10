package com.example.foodinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!


    /*
        наследниках выносить все инициализации RecyclerView и их адаптеров
        подписывать все View на данные из viewModel
        добавлять к ним clickListener и т.д.
     */
    abstract fun initUI()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // вычитал что лучше это делать в onViewCreated, а не в onCreateView
        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}