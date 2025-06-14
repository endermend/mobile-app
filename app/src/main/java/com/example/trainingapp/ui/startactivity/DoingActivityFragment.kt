package com.example.trainingapp.ui.startactivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.trainingapp.MainActivity
import com.example.trainingapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DoingActivityFragment : Fragment() {

    companion object {
        fun newInstance() = DoingActivityFragment()
    }

    private val viewModel by activityViewModels<StartActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_doing_activity, container, false)

        view.findViewById<TextView>(R.id.activity_length).text = "0 км"

        viewModel.type.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.activity_type).text = it
        }
        viewModel.formattedTime.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.activity_time).text = it
        }
        view.findViewById<FloatingActionButton>(R.id.finish_activity).setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
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