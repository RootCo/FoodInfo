package com.example.foodinfo.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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

    private var stopTrackingCallback: ((Float, Float, Boolean) -> Unit)? = null


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
            values = listOf(field, maxValue)
            binding.rsRange.values = values
            binding.rsRange.valueFrom = field
        }

    var maxValue: Float = DEFAULT_MAX_VALUE
        set(value) {
            field = value
            values = listOf(minValue, field)
            binding.rsRange.values = values
            binding.rsRange.valueTo = field
        }

    var questionMarkVisible: Boolean = false
        set(value) {
            field = value
            if (value) {
                binding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_question_mark, 0, 0, 0
                )
            } else {
                binding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0
                )
            }
        }

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


    var values: List<Float> = listOf(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE).also {
        binding.rsRange.values = it
        binding.tvRangeStart.text = DEFAULT_MIN_VALUE.toInt().toString()
        binding.tvRangeEnd.text = DEFAULT_MAX_VALUE.toInt().toString()
    }
        private set(value) {
            field = value
            binding.tvRangeStart.text = field.min().toInt().toString()
            binding.tvRangeEnd.text = field.max().toInt().toString()
        }

    var minCurrent: Float = DEFAULT_MIN_VALUE
        private set
        get() = values.min()

    var maxCurrent: Float = DEFAULT_MAX_VALUE
        private set
        get() = values.max()


    private val onValueChangedCallback = RangeSlider.OnChangeListener { slider, _, _ ->
        values = slider.values
    }

    private val onStopTrackingCallback = object : RangeSlider.OnSliderTouchListener {
        override fun onStartTrackingTouch(slider: RangeSlider) {}

        override fun onStopTrackingTouch(slider: RangeSlider) {
            stopTrackingCallback?.invoke(
                minCurrent,
                maxCurrent,
                minValue == minCurrent && maxValue == maxCurrent
            )
        }
    }


    fun addHeaderClickCallback(callback: (String) -> Unit) {
        binding.tvHeader.setOnClickListener { callback(binding.tvHeader.text.toString()) }
    }

    fun addStopTrackingCallback(callback: (Float, Float, Boolean) -> Unit) {
        stopTrackingCallback = callback
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
