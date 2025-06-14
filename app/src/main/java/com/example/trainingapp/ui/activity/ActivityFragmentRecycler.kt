package com.example.trainingapp.ui.activity

import ListItemOnClickListener
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.ui.activity.details.DetailsViewModel
import com.example.trainingapp.ui.activity.models.ActivityUIModel
import com.example.trainingapp.ui.activity.models.ActivityViewModel


abstract class ActivityFragmentRecycler : Fragment() {
    protected val viewModel by viewModels<ActivityViewModel>()
    protected abstract fun updateRecyclerView(listItemsAdapter: ListItemsAdapter)
    protected fun createRecycler(view: View, inflater: LayoutInflater): ListItemsAdapter {
        val listItemsAdapter by lazy {
            ListItemsAdapter(inflater, object : ListItemOnClickListener {
                override fun oniItemClick(activityUIModel: ActivityUIModel) {
                    goToDetails(activityUIModel)
                }
            })
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_view)
        val dividerItemDecoration = VerticalSpaceItemDecoration(
            resources.getDimension(R.dimen.spacing_large).toInt(),
            resources.getDimension(R.dimen.activity_vertical_margin).toInt()
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = listItemsAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        updateRecyclerView(listItemsAdapter)
        return listItemsAdapter
    }

    abstract fun getDetails(): Fragment

    protected fun goToDetails(activityUIModel: ActivityUIModel) {
        val detailsModel by activityViewModels<DetailsViewModel>()
        detailsModel.activity.value = activityUIModel
        val details = getDetails()
        val transition = parentFragmentManager.beginTransaction()
        transition.replace(R.id.nav_host_fragment_activity_main, details)
        transition.addToBackStack(null)
        transition.commit()
    }
}