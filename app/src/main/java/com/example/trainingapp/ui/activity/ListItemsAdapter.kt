package com.example.trainingapp.ui.activity

import com.example.trainingapp.ui.activity.holders.ItemClickListener
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.ui.ListItemViewHolder
import com.example.trainingapp.ui.activity.holders.ActivityViewHolder
import com.example.trainingapp.ui.activity.holders.TitleViewHolder
import com.example.trainingapp.ui.activity.models.ActivityUIModel
import com.example.trainingapp.ui.activity.models.ListItemUIModel

enum class ViewTypes(val type: Int) {
    GROUP(0), ACTIVITY(1)
}

class ListItemsAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: ListItemActivityOnClickListener,
) : RecyclerView.Adapter<ListItemViewHolder>() {

    private val dataList = mutableListOf<ListItemUIModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDataList: List<ListItemUIModel>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return when (viewType) {
            ViewTypes.ACTIVITY.type -> {
                val view = layoutInflater.inflate(R.layout.item_activity, parent, false)
                ActivityViewHolder(view, object : ItemClickListener {
                    override fun oniItemClick(activityUIModel: ActivityUIModel) {
                        onClickListener.oniItemClick(activityUIModel)
                    }

                })
            }

            ViewTypes.GROUP.type -> {
                val view = layoutInflater.inflate(R.layout.item_title, parent, false)
                TitleViewHolder(view)
            }

            else -> throw java.lang.IllegalArgumentException("Unknown View Type requested: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is ListItemUIModel.Title -> ViewTypes.GROUP.type
            is ListItemUIModel.Activity -> ViewTypes.ACTIVITY.type
            else -> throw java.lang.IllegalArgumentException("Unknown Biew Type requested: ${dataList[position]}")
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }
}