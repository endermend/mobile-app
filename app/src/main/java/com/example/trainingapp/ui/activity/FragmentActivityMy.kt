package com.example.trainingapp.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainingapp.R
import com.example.trainingapp.ui.activity.details.FragmentMyDetails
import com.example.trainingapp.ui.activity.models.ActivityUIModel
import com.example.trainingapp.ui.activity.models.ListItemUIModel

class FragmentActivityMy : ActivityFragmentRecycler() {
    override fun updateRecyclerView(listItemsAdapter: ListItemsAdapter) {
        val recyclerList = viewModel.getRecyclerList { it.data._email == "user" }
        listItemsAdapter.setData(recyclerList.map { activity ->
            if (activity is ListItemUIModel.Activity) ListItemUIModel.Activity(
                ActivityUIModel(
                    activity.data._length,
                    activity.data._time,
                    activity.data._type,
                    activity.data._date,
                    "",
                    activity.data._comment
                )
            ) else activity
        })
    }

    override fun getDetails(): Fragment {
        return FragmentMyDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_activity_my, container, false)
        createRecycler(view, inflater)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         * @return A new instance of fragment ActivityAll.
         */
        @JvmStatic
        fun newInstance() = FragmentActivityMy()
    }
}