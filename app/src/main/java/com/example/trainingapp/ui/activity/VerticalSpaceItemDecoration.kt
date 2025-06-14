package com.example.trainingapp.ui.activity

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.NO_POSITION


class VerticalSpaceItemDecoration(
    private val verticalSpaceHeightGroup: Int,
    private val verticalSpaceHeightItem: Int,
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != NO_POSITION && position != 0) {
            outRect.top =
                if (parent.adapter?.getItemViewType(position) == ViewTypes.GROUP.type) verticalSpaceHeightGroup
                else verticalSpaceHeightItem
        }

    }
}