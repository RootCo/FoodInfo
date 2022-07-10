package com.example.foodinfo.utils

import android.app.AlertDialog
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


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

/*
     для фрагментов, которые лучше закрыть, уведомив пользователя, если нету данных для
     его полноценного функционирования
     например, свернули приложение на фрагменте с рецептами, почислили кэш на телефоне
     через условный ccleaner, выключили интернет, вернулись в приложение
     локальной бд нет, к серверу не подключиться, на экран выводить нечего - просим
     пользователя проверить подключение, он нажимает ок - закрываем фрагмент
 */
fun Fragment.handleNoData() {
    this.requireActivity().let { activity ->
        val alertBuilder = AlertDialog.Builder(activity)
        alertBuilder.setTitle("gg")
        alertBuilder.setMessage("inet rip")
        alertBuilder.setPositiveButton("OK", null)

        // TODO закрыть приложение по нажатию ОК кнопки
    }
}

