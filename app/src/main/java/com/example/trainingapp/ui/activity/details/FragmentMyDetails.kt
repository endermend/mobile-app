package com.example.trainingapp.ui.activity.details

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import com.example.trainingapp.R
import com.example.trainingapp.databinding.FragmentActivityMyDetailsBinding


class FragmentMyDetails : Fragment(), Toolbar.OnMenuItemClickListener {
    private var _binding: FragmentActivityMyDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentActivityMyDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val toolbar = root.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setOnMenuItemClickListener(this)
        return root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        Toast.makeText(requireContext(), "U should add listeners", Toast.LENGTH_LONG).show()
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(requireContext(), "I don't know how that's happened", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_toolbar_menu, menu)
        menu.iterator().forEach {
            val icon = it.icon
            icon?.setColorFilter(requireContext().getColor(R.color.purple_500), PorterDuff.Mode.SRC_IN)
            it.setIcon(icon)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}