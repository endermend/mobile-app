package com.example.trainingapp.ui.personal

import android.content.Intent
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trainingapp.R
import com.example.trainingapp.WelcomeActivity
import com.example.trainingapp.databinding.FragmentPersonalBinding

class PersonalFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private var _binding: FragmentPersonalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val personalViewModel = ViewModelProvider(this)[PersonalViewModel::class.java]

        _binding = FragmentPersonalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val toolbar = root.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener(this)
        binding.changePassword.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_personal_to_navigation_change_password
            )
        }
        binding.exitButton.setOnClickListener {
            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            startActivity(intent)
        }
        personalViewModel.login.observe(viewLifecycleOwner) {
            binding.editTextLogin.editText?.setText(it)
        }
        personalViewModel.nickname.observe(viewLifecycleOwner) {
            binding.editTextNickName.editText?.setText(it)
        }
        return root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        Toast.makeText(requireContext(), "U should add listeners", Toast.LENGTH_LONG).show()
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(
                    requireContext(), "I don't know how that's happened", Toast.LENGTH_LONG
                ).show()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.personal_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}