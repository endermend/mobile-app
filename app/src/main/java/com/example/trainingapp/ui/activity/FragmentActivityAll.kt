package com.example.trainingapp.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainingapp.R


class FragmentActivityAll : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_all, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         * @return A new instance of fragment ActivityAll.
         */
        @JvmStatic
        fun newInstance() = FragmentActivityAll()
    }
}