package com.example.foodinfo.utils

import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.foodinfo.R
import com.example.foodinfo.databinding.DialogDescriptionBinding
import com.example.foodinfo.repository.model.SVGModel
import com.example.foodinfo.utils.glide.GlideApp
import com.google.android.material.bottomsheet.BottomSheetDialog


/*
    activity.currentFocus вместо view т.к. после view.requestFocus() фокус может
    не успеть поставиться на view и showSoftInput вернет false, не отобразив
    клавиатуру, т.к. принял неправильную View, на которой не стоял фокус
 */
fun Fragment.showKeyboard(view: View) {
    this.requireActivity().let { activity ->
        view.requestFocus()
        ContextCompat.getSystemService(activity, InputMethodManager::class.java)
            ?.showSoftInput(activity.currentFocus, InputMethodManager.SHOW_IMPLICIT)
    }
}

/*
    HIDE_IMPLICIT_ONLY не подходит, закрывает только клавиатуру, которую я программно
    открыл. Но если нажать кнопку назад (сверху слева) то клавиатура, открытая при
    явном нажатии на EditText/SearchView останется и переедет на другой фрагмент
 */
fun Fragment.hideKeyboard() {
    this.requireActivity().let { activity ->
        val currentFocus = activity.currentFocus ?: return
        ContextCompat.getSystemService(activity, InputMethodManager::class.java)
            ?.hideSoftInputFromWindow(
                currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
    }
}

fun Fragment.showDescriptionDialog(
    header: String,
    description: String,
    preview: SVGModel
) {
    val dialogBinding = DialogDescriptionBinding.inflate(
        LayoutInflater.from(requireContext())
    ).apply {
        GlideApp.with(requireContext())
            .load(preview)
            .error(R.drawable.ic_no_image)
            .into(ivPreview)
        tvHeader.text = header
        tvDescription.text = description
    }

    BottomSheetDialog(requireContext(), R.style.BottomSheetDialog).apply {
        setContentView(dialogBinding.root)
    }.show()
}

