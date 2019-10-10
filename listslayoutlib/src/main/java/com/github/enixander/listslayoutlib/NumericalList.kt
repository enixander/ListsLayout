package com.github.enixander.listslayoutlib

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class NumericalList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var marginBetween: Int = 16
    private var useDot: Boolean = false

    private var horizontalLayoutParams: LayoutParams
    private var numberLayoutParams: LayoutParams
    private var valueLayoutParams: LayoutParams

    init {
        orientation = VERTICAL
        layoutParams = getListLayoutParam()

        val attributeValues = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.NumericalList,
            defStyleAttr,
            0
        )
        marginBetween =
            attributeValues.getDimensionPixelSize(R.styleable.NumericalList_list_marginBetween, 16)
        useDot = attributeValues.getBoolean(R.styleable.NumericalList_list_useDot, false)

        horizontalLayoutParams = getListLayoutParam()
        numberLayoutParams = getNumberLayoutParam()
        valueLayoutParams = getValueLayoutParam()
    }

    fun setData(items: Array<String>) {
        for (index in items.indices) {
            val horizontalLinearLayout = setupHorizontalLinearLayout(index, horizontalLayoutParams)
            val numberView = setupNumberView(index, numberLayoutParams)
            val valueView = setupValueView(items[index], valueLayoutParams)

            horizontalLinearLayout.addView(numberView)
            horizontalLinearLayout.addView(valueView)
            addView(horizontalLinearLayout)
        }
    }

    private fun setupHorizontalLinearLayout(index: Int, horizontalLayoutParams: LayoutParams): LinearLayout {
        val horizontalLinearLayout = LinearLayout(context)
        if (index > 0) horizontalLayoutParams.topMargin = marginBetween

        with(horizontalLinearLayout, {
            orientation = HORIZONTAL
            layoutParams = horizontalLayoutParams
        })
        return horizontalLinearLayout
    }

    private fun setupNumberView(index: Int, numberLayoutParams: LayoutParams): TextView {
        val numberView = TextView(context)
        val value = getNumberText(index)
        with(numberView, {
            layoutParams = numberLayoutParams
            text = value
        })
        return numberView
    }

    private fun setupValueView(value: String, valueLayoutParams: LayoutParams): TextView {
        val valueView = TextView(context)
        with(valueView, {
            setPadding(16, 0, 0, 0)
            layoutParams = valueLayoutParams
            text = value
        })
        return valueView
    }

    private fun getListLayoutParam(): LayoutParams {
        return LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun getNumberLayoutParam(): LayoutParams {
        return LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun getValueLayoutParam(): LayoutParams {
        return LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun getNumberText(index: Int): String {
        val number = (index + 1).toString()
        return if (useDot) number.plus(".") else number
    }
}