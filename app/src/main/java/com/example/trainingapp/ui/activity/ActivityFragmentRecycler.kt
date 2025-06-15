package com.example.trainingapp.ui.activity

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.ui.activity.details.DetailsViewModel
import com.example.trainingapp.ui.activity.models.ActivityUIModel
import com.example.trainingapp.ui.activity.models.ActivityViewModel


abstract class ActivityFragmentRecycler : Fragment() {
    private val mActivityViewModel by viewModels<com.example.trainingapp.data.ActivityViewModel>()
    protected val viewModel by viewModels<ActivityViewModel>()
    protected abstract fun updateRecyclerView(listItemsAdapter: ListItemsAdapter)


    protected fun createRecycler(view: View, inflater: LayoutInflater): ListItemsAdapter {
        mActivityViewModel.readAllActivities.observe(viewLifecycleOwner){
            viewModel.updateActivities(it)
        }
        val listItemsAdapter by lazy {
            ListItemsAdapter(inflater, object : ListItemActivityOnClickListener {
                override fun oniItemClick(activityUIModel: ActivityUIModel) {
                    goToDetails(activityUIModel)
                }
            })
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_view)
        val dividerItemDecoration = VerticalSpaceItemDecoration(
            resources.getDimension(R.dimen.spacing_normal).toInt(),
            resources.getDimension(R.dimen.activity_vertical_margin).toInt()
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = listItemsAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        viewModel.activities.observe(viewLifecycleOwner){
            updateRecyclerView(listItemsAdapter)
        }
        return listItemsAdapter
    }

    abstract fun detailsNavigation() : Int

    protected fun goToDetails(activityUIModel: ActivityUIModel) {
        val detailsModel by activityViewModels<DetailsViewModel>()
        detailsModel.activity.value = activityUIModel
        detailsModel.id.postValue(activityUIModel.id)
        findNavController().navigate(detailsNavigation())
    }
}