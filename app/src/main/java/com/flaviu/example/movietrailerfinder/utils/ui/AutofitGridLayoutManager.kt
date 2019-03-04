package com.flaviu.example.movietrailerfinder.utils.ui

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AutofitGridLayoutManager(
    context: Context,
    columnWidth: Int,
    orientation: Int,
    reverseLayout: Boolean
) : GridLayoutManager(
    context,
    1,
    orientation,
    reverseLayout
) {
    private var columnWidth: Int = 0
    private var isColumnWidthChanged = true
    private var currentWidth = 0
    private var currentHeight = 0

    init {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }

    private fun checkedColumnWidth(context: Context, columnWidth: Int): Int {
        var newColumnWidth = columnWidth
        if (newColumnWidth <= 0) {
            newColumnWidth = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 48f,
                context.resources.displayMetrics
            ).toInt()
        }
        return newColumnWidth
    }

    private fun setColumnWidth(newColumnWidth: Int) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth
            isColumnWidthChanged = true
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        if (currentWidth == width || currentHeight == height) {
            isColumnWidthChanged = true
        }
        currentWidth = width
        currentHeight = height
        if (isColumnWidthChanged && columnWidth > 0 && width > 0 && height > 0) {
            val totalSpace: Int = if (orientation == LinearLayoutManager.VERTICAL) {
                width - paddingRight - paddingLeft
            } else {
                height - paddingTop - paddingBottom
            }
            val spanCount = Math.max(1, totalSpace / columnWidth)
            setSpanCount(spanCount)
            isColumnWidthChanged = false
        }
        super.onLayoutChildren(recycler, state)
    }
}