package com.example.testairtickets.fragments

import Classes.InputFilter
import Classes.DataContainer
import android.app.DatePickerDialog
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
import com.example.testairtickets.filters_activity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit.SearchAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

        val fromData = DataContainer.getFromText(requireContext())
        val whereData = DataContainer.getWhereText(requireContext())

        binding.searchFromEdit.setText(fromData)
        binding.searchWhereEdit.setText(whereData)

        binding.searchFromEdit.filters = arrayOf(InputFilter())
        binding.searchWhereEdit.filters = arrayOf(InputFilter())

        dialog = BottomSheetDialog(requireContext())
        clearEdits()
        goBack()
        setDate()
        placeSwitch()
        goFilters()
        flightsInfo()
        showAllTickets()
        setBackDate()
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
        val currentSavedDate = currentDate.format(DateTimeFormatter.ofPattern("dd MMMM", Locale("ru")))
        val currentSavedDateText = currentSavedDate.toString()
        DataContainer.saveDate(requireContext(), currentSavedDateText)
        binding.currentDateText.text = formattedDate

        binding.flightDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val selectedDate = LocalDate.of(year, month + 1, day)
                    val formattedSelectedDate = selectedDate.format(DateTimeFormatter.ofPattern("dd MMM, EEE", Locale("ru")))
                    val savedDate = selectedDate.format(DateTimeFormatter.ofPattern("dd MMMM", Locale("ru")))
                    val formattedSavedDate = savedDate.toString()
                    DataContainer.saveDate(requireContext(), formattedSavedDate)
                    binding.currentDateText.text = formattedSelectedDate
                },
                currentDate.year,
                currentDate.monthValue - 1,
                currentDate.dayOfMonth
            )
            datePickerDialog.show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setBackDate() {
        binding.backDateButton.setOnClickListener {
            val currentDate = LocalDate.now()
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val selectedDate = LocalDate.of(year, month + 1, day)
                    val formattedSelectedDate = selectedDate.format(DateTimeFormatter.ofPattern("dd MMM, EEE", Locale("ru")))
                    binding.backDateText.text = formattedSelectedDate
                },
                currentDate.year,
                currentDate.monthValue - 1,
                currentDate.dayOfMonth
            )
            datePickerDialog.show()
        }
    }

    private fun placeSwitch() {
        binding.placeSwitcher.setOnClickListener {
            val firstPlace = binding.searchFromEdit.text.toString()
            val secondPlace = binding.searchWhereEdit.text.toString()
            binding.searchWhereEdit.setText(firstPlace)
            binding.searchFromEdit.setText(secondPlace)
            DataContainer.saveData(requireContext(), secondPlace, firstPlace)
        }
    }

    private fun goFilters() {
        binding.searchFiltersButton.setOnClickListener {
            val intent = Intent(requireContext(), filters_activity::class.java)
            startActivity(intent)
        }
    }

    private fun flightsInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val flightAPI = retrofit.create(SearchAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = flightAPI.getTickets()

            if (response.isSuccessful){
                val searchFlights = response.body()

                if (searchFlights != null){
                    withContext(Dispatchers.Main){

                        for (searchFlight in searchFlights.searchTicketsOffers){
                            when (searchFlight.searchId){
                                1 -> {
                                    val timeRanges = searchFlight.searchFlightRange.take(6)
                                    var timeString = timeRanges.joinToString(separator = " ") {time -> time}

                                    if (searchFlight.searchFlightRange.size > 6){
                                        timeString += "..."
                                    }
                                    binding.flightFirstHeader.text = searchFlight.searchTitle
                                    binding.flightFirstPrice.text = searchFlight.searchPrice.searchValue.toString() + " ₽ >"
                                    binding.flightFirstTime.text = timeString
                                    binding.popularFirst.visibility = View.VISIBLE
                                }

                                10 -> {
                                    val timeRanges = searchFlight.searchFlightRange.take(6)
                                    var timeString = timeRanges.joinToString(separator = " ") {time -> time}

                                    if (searchFlight.searchFlightRange.size > 6){
                                        timeString += "..."
                                    }
                                    binding.flightSecondHeader.text = searchFlight.searchTitle
                                    binding.flightSecondPrice.text = searchFlight.searchPrice.searchValue.toString() + " ₽ >"
                                    binding.flightSecondTime.text = timeString
                                    binding.popularSecond.visibility = View.VISIBLE
                                }

                                30 -> {
                                    val timeRanges = searchFlight.searchFlightRange.take(6)
                                    var timeString = timeRanges.joinToString(separator = " ") {time -> time}

                                    if (searchFlight.searchFlightRange.size > 6){
                                        timeString += "..."
                                    }
                                    binding.flightThirdHeader.text = searchFlight.searchTitle
                                    binding.flightThirdPrice.text = searchFlight.searchPrice.searchValue.toString() + " ₽ >"
                                    binding.flightThirdTime.text = timeString
                                    binding.popularThird.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showAllTickets() {
        binding.allTicketsButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.airticketsFrame, AllTicketsFragment())
            fragmentTransaction.commit()
        }
    }
}