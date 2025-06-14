package com.example.trainingapp.ui.activity

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainingapp.NewActivityActivity
import com.example.trainingapp.R
import com.example.trainingapp.databinding.FragmentActivityBinding
import com.google.android.material.tabs.TabLayoutMediator


class ActivityFragment : Fragment() {
    private val fragList =
        listOf(FragmentActivityMy.newInstance(), FragmentActivityAll.newInstance())

    private var _binding: FragmentActivityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = VpAdapter(requireActivity(), fragList)

        binding.vp2.adapter = adapter
        val fragTitleList = listOf(getString(R.string.title_my), getString(R.string.title_users))
        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab, pos ->
            tab.text = fragTitleList[pos]
        }.attach()
        binding.startActivityButton.apply{
            setColorFilter(resources.getColor(R.color.white, requireContext().theme), PorterDuff.Mode.SRC_IN)
            setOnClickListener{
                val intent = Intent(requireContext(), NewActivityActivity::class.java)
                startActivity(intent)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}