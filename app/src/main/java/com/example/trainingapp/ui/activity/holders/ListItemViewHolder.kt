package com.example.trainingapp.ui.activity.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.ui.activity.models.ListItemUIModel

abstract class ListItemViewHolder(containerView: View) :
    RecyclerView.ViewHolder(containerView) {
    abstract fun bindData(listItem : ListItemUIModel)
}