package com.example.testairtickets.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.testairtickets.R
import com.example.testairtickets.databinding.FragmentSearchBinding
import com.example.testairtickets.databinding.FragmentSubscribesBinding
import com.example.testairtickets.filters_activity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var dialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = BottomSheetDialog(requireContext())
        clearEdits()
        goBack()
        setDate()
        placeSwitch()
        goFilters()
    }

    private fun clearEdits() {

        binding.searchFromEdit.addTextChangedListener { text ->
            binding.cancelButton.isVisible = !text.isNullOrEmpty()
        }
        binding.searchWhereEdit.addTextChangedListener { text ->
            binding.cancelButton.isVisible = !text.isNullOrEmpty()
        }

        binding.cancelButton.setOnClickListener {
            binding.searchFromEdit.text = null
            binding.searchWhereEdit.text = null
            binding.cancelButton.visibility = View.INVISIBLE
        }
    }

    private fun goBack() {
        binding.searchBackButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.airticketsFrame, AirTicketsFragment())
            fragmentTransaction.commit()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDate() {
        val currentDate = LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd MMM, EEE", Locale("ru")))
        binding.currentDateText.text = formattedDate
    }

    private fun placeSwitch() {
        binding.placeSwitcher.setOnClickListener {
            val firstPlace = binding.searchFromEdit.text.toString()
            val secondPlace = binding.searchWhereEdit.text.toString()
            binding.searchWhereEdit.setText(firstPlace)
            binding.searchFromEdit.setText(secondPlace)
        }
    }

    private fun goFilters() {
        binding.searchFiltersButton.setOnClickListener {
            val intent = Intent(requireContext(), filters_activity::class.java)
            startActivity(intent)
        }
    }
}