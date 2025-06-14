package com.example.trainingapp.ui.activity.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.trainingapp.R
import com.example.trainingapp.databinding.FragmentActivityAllDetailsBinding
import java.time.format.DateTimeFormatter


class FragmentAllDetails : Fragment(), Toolbar.OnMenuItemClickListener {
    private val detailsModel by activityViewModels<DetailsViewModel>()
    private var _binding: FragmentActivityAllDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentActivityAllDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val toolbar = root.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        detailsModel.activity.observe(viewLifecycleOwner) {
            toolbar.title = it.type(requireContext())
            binding.itemActivityDate.text = it.date
            binding.itemActivityTime.text = it.time
            binding.itemActivityFinish.text = it._date.format(DateTimeFormatter.ofPattern("HH:mm"))
            binding.itemActivityStart.text = it._date.plusMinutes((-it._time).toLong()).format(DateTimeFormatter.ofPattern("HH:mm"))
            binding.itemActivityLength.text = it.length
            binding.itemActivityEmail.text = it.email
            binding.itemActivityComment.setText(it.comment.ifEmpty { "Тут пустовато" })
        }
        return root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        Toast.makeText(requireContext(), "WTF", Toast.LENGTH_LONG).show()
        return super.onContextItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}