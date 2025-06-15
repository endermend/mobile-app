package com.example.trainingapp.ui.startactivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.trainingapp.MainActivity
import com.example.trainingapp.R
import com.example.trainingapp.data.Activity
import com.example.trainingapp.data.ActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime

class DoingActivityFragment : Fragment() {
    private val mActivityViewModel by viewModels<ActivityViewModel>()

    companion object {
        fun newInstance() = DoingActivityFragment()
    }

    private val viewModel by activityViewModels<StartActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_doing_activity, container, false)

        viewModel.formattedLength.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.activity_length).text = it
        }

        //mActivityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        viewModel.formattedType(requireContext()).observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.activity_type).text = it
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.activity_time).text = it
        }
        view.findViewById<FloatingActionButton>(R.id.finish_activity).setOnClickListener {
            val activity = Activity(
                0,
                viewModel.type.value!!,
                viewModel.start.value!!,
                LocalDateTime.now(),
                viewModel.length.value!!,
            )
            val intent = Intent(requireContext(), MainActivity::class.java)
            mActivityViewModel.addActivity(activity)
            startActivity(intent)
        }
        view.findViewById<FloatingActionButton>(R.id.pause_activity).setOnClickListener {
            viewModel.stopTimer()
        }
        viewModel.startTimer()
        return view
    }

    override fun onDestroy() {
        viewModel.stopTimer()
        viewModel.resetTimer()
        super.onDestroy()
    }
}