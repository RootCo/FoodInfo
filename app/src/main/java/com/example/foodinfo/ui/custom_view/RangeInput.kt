package com.example.foodinfo.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.foodinfo.R
import com.example.foodinfo.databinding.CvRangeInputFieldBinding
import com.google.android.material.slider.RangeSlider


class RangeInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {


    companion object {
        private const val DEFAULT_HEADER: String = "duration"
        private const val DEFAULT_MEASURE: String = "sec"
        private const val DEFAULT_STEP_SIZE: Float = 1f
        private const val DEFAULT_MIN_VALUE: Float = 0f
        private const val DEFAULT_MAX_VALUE: Float = 100f
        private const val DEFAULT_QUESTION_MARK_VISIBILITY: Boolean = false
        private const val DEFAULT_HEADER_COLOR: Int = R.attr.appMainFontColor
        private const val DEFAULT_HEADER_TEXT_SIZE: Int = R.dimen.text_20
    }


    private val binding = CvRangeInputFieldBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var stopTrackingCallbacks: ArrayList<((Float, Float, Boolean) -> Unit)> =
        arrayListOf()


    var headerTextColor: Int = DEFAULT_HEADER_COLOR
        set(value) {
            field = value
            val typedValue = TypedValue()
            val color: Int =
                if (!context.theme.resolveAttribute(value, typedValue, true)) {
                    resources.getColor(value, context.theme)
                } else {
                    ContextCompat.getColor(context, typedValue.resourceId)
                }
            binding.tvHeader.setTextColor(color)
            binding.tvMeasure.setTextColor(color)
        }

    var headerFontTextSize: Int = DEFAULT_HEADER_TEXT_SIZE
        set(value) {
            field = value
            val size = resources.getDimensionPixelSize(field).toFloat()
            binding.tvHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
            binding.tvMeasure.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
        }

    var questionMarkVisible: Boolean = false
        set(value) {
            field = value
            binding.ivQuestionMark.isVisible = field
        }

    var header: String = DEFAULT_HEADER
        set(value) {
            field = value
            binding.tvHeader.text = field
        }

    var measure: String = DEFAULT_MEASURE
        set(value) {
            field = value
            binding.tvMeasure.text = field
        }

    var stepSize: Float = DEFAULT_STEP_SIZE
        set(value) {
            field = value
            binding.rsRange.stepSize = field
        }

    var minValue: Float = DEFAULT_MIN_VALUE
        set(value) {
            field = value
            if (field > minCurrent) {
                binding.rsRange.values = listOf(field, maxCurrent)
            }
            binding.rsRange.valueFrom = field
        }

    var maxValue: Float = DEFAULT_MAX_VALUE
        set(value) {
            field = value
            if (field < maxCurrent) {
                binding.rsRange.values = listOf(minCurrent, field)
            }
            binding.rsRange.valueTo = field
        }

    var minCurrent: Float = DEFAULT_MIN_VALUE
        set(value) {
            field = if (value < minValue) minValue else value
            binding.rsRange.values = listOf(field, maxCurrent)
        }
        get() = binding.rsRange.values.min()

    var maxCurrent: Float = DEFAULT_MAX_VALUE
        set(value) {
            field = if (value > maxValue) maxValue else value
            binding.rsRange.values = listOf(minCurrent, field)
        }
        get() = binding.rsRange.values.max()


    private val onValueChangedCallback = RangeSlider.OnChangeListener { _, _, _ ->
        binding.tvRangeStart.text = minCurrent.toInt().toString()
        binding.tvRangeEnd.text = maxCurrent.toInt().toString()
    }


    private val onStopTrackingCallback = object : RangeSlider.OnSliderTouchListener {
        override fun onStartTrackingTouch(slider: RangeSlider) {}

        override fun onStopTrackingTouch(slider: RangeSlider) {
            stopTrackingCallbacks.forEach { callback ->
                callback.invoke(
                    minCurrent,
                    maxCurrent,
                    minValue == minCurrent && maxValue == maxCurrent
                )
            }
        }
    }


    fun addHeaderClickCallback(callback: (String) -> Unit) {
        binding.clHeader.setOnClickListener { callback(binding.tvHeader.text.toString()) }
    }

    fun addStopTrackingCallback(callback: (Float, Float, Boolean) -> Unit) {
        stopTrackingCallbacks.add(callback)
    }


    init {
        context.obtainStyledAttributes(attrs, R.styleable.RangeInput, 0, 0).apply {
            try {
                header = getString(
                    R.styleable.RangeInput_header
                ) ?: DEFAULT_HEADER
                measure = getString(
                    R.styleable.RangeInput_measure
                ) ?: DEFAULT_MEASURE
                stepSize = getFloat(
                    R.styleable.RangeInput_stepSize,
                    DEFAULT_STEP_SIZE
                )
                minValue = getFloat(
                    R.styleable.RangeInput_minValue,
                    DEFAULT_MIN_VALUE
                )
                maxValue = getFloat(
                    R.styleable.RangeInput_maxValue,
                    DEFAULT_MAX_VALUE
                )
                questionMarkVisible = getBoolean(
                    R.styleable.RangeInput_questionMarkVisible,
                    DEFAULT_QUESTION_MARK_VISIBILITY
                )

                headerTextColor = getResourceId(
                    R.styleable.RangeInput_headerTextColor,
                    DEFAULT_HEADER_COLOR
                )
                headerFontTextSize = getResourceId(
                    R.styleable.RangeInput_headerTextSize,
                    DEFAULT_HEADER_TEXT_SIZE
                )
            } finally {
                recycle()
            }
        }
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        binding.rsRange.addOnChangeListener(onValueChangedCallback)
        binding.rsRange.addOnSliderTouchListener(onStopTrackingCallback)
    }
}
