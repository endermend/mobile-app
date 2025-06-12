package com.example.trainingapp.ui.activity.holders

import android.view.View
import android.widget.TextView
import com.example.trainingapp.R
import com.example.trainingapp.ui.activity.models.ListItemUIModel


class TitleViewHolder(containerView: View) : ListItemViewHolder(containerView) {
    private val titleView: TextView by lazy {
        containerView.findViewById(R.id.item_recycler_title)
    }

    override fun bindData(listItem: ListItemUIModel) {
        require(listItem is ListItemUIModel.Title){
            "Expected ListItemUIModel.Title"
        }
        titleView.text = listItem.title
    }
}