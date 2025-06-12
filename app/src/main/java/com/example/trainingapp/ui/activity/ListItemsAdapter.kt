package com.example.trainingapp.ui.activity

import ItemClickListener
import ListItemOnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.ui.activity.holders.ActivityViewHolder
import com.example.trainingapp.ui.activity.holders.ListItemViewHolder
import com.example.trainingapp.ui.activity.holders.TitleViewHolder
import com.example.trainingapp.ui.activity.models.ActivityUIModel
import com.example.trainingapp.ui.activity.models.ListItemUIModel

private const val VIEW_TYPE_GROUP = 0
private const val VIEW_TYPE_ACTIVITY = 1

class ListItemsAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: ListItemOnClickListener,
) : RecyclerView.Adapter<ListItemViewHolder>() {

    private val dataList = mutableListOf<ListItemUIModel>()

    fun setData(newDataList: List<ListItemUIModel>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return when (viewType) {
            VIEW_TYPE_ACTIVITY -> {
                val view = layoutInflater.inflate(R.layout.item_activity, parent, false)
                ActivityViewHolder(view, object : ItemClickListener {
                    override fun oniItemClick(activityUIModel: ActivityUIModel) {
                        onClickListener.oniItemClick(activityUIModel)
                    }

                })
            }

            VIEW_TYPE_GROUP -> {
                val view = layoutInflater.inflate(R.layout.item_title, parent, false)
                TitleViewHolder(view)
            }
            else -> throw java.lang.IllegalArgumentException("Unknown View Type requested: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is ListItemUIModel.Title -> VIEW_TYPE_GROUP
            is ListItemUIModel.Activity -> VIEW_TYPE_ACTIVITY
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }
}