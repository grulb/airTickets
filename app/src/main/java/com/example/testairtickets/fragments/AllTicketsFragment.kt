package com.example.testairtickets.fragments

import Classes.DataContainer
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.testairtickets.R
import com.example.testairtickets.databinding.FragmentAllTicketsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit.AllTicketsAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AllTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAllTicketsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fromData = DataContainer.getFromText(requireContext())
        val whereData = DataContainer.getWhereText(requireContext())
        val flightDate = DataContainer.getSavedFlightDate(requireContext())
        binding.fromHeader.text = "${fromData}-${whereData}"
        binding.passHeader.setText(flightDate + ", 1 пассажир")

        allTicketsInfo()
        goBack()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun allTicketsInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val allTicketsAPI = retrofit.create(AllTicketsAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = allTicketsAPI.getAllTickets()

            if (response.isSuccessful) {
                val allTickets = response.body()

                if (allTickets != null) {
                    withContext(Dispatchers.Main){
                        for (allTicket in allTickets.allTicketsList){
                            when (allTicket.allId){
                                100 -> {
                                    val depDate = allTicket.allDeparture.allDepDate
                                    val depParsedDate = LocalDateTime.parse(depDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val depTime = LocalDateTime.parse(depDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val arrDate = allTicket.allArrival.allArrDate
                                    val arrParsedDate = LocalDateTime.parse(arrDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val arrTime = LocalDateTime.parse(arrDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val flightTime = Duration.between(depParsedDate, arrParsedDate)
                                    val hours = flightTime.toHours()
                                    val minutes = flightTime.toMinutes() % 60
                                    val hoursWithFraction = hours + (minutes / 60.0)
                                    var flightTimeString = "${String.format("%.1f", hoursWithFraction)}ч в пути"

                                    if (allTicket.allTransfer == true) {
                                        flightTimeString += "/Без пересадок"
                                    }

                                    if (allTicket.allBadge != null) {
                                        binding.firstBadge.visibility = View.VISIBLE
                                        binding.firstBadge.text = allTicket.allBadge
                                    }

                                    binding.firstPrice.text = allTicket.allPrice.allValue.toString() + " ₽"
                                    binding.firstFlightStart.text = depTime
                                    binding.firstAirportStart.text = allTicket.allDeparture.allDepAirport
                                    binding.firstFlightEnd.text = arrTime
                                    binding.firstAirportEnd.text = allTicket.allArrival.allArrAirport
                                    binding.firstAddText.text = flightTimeString
                                    binding.firstLayout.visibility = View.VISIBLE
                                    binding.allTicketsLoading.visibility = View.INVISIBLE
                                }

                                102 -> {
                                    val depDate = allTicket.allDeparture.allDepDate
                                    val depParsedDate = LocalDateTime.parse(depDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val depTime = LocalDateTime.parse(depDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val arrDate = allTicket.allArrival.allArrDate
                                    val arrParsedDate = LocalDateTime.parse(arrDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val arrTime = LocalDateTime.parse(arrDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val flightTime = Duration.between(depParsedDate, arrParsedDate)
                                    val hours = flightTime.toHours()
                                    val minutes = flightTime.toMinutes() % 60
                                    val hoursWithFraction = hours + (minutes / 60.0)
                                    var flightTimeString = "${String.format("%.1f", hoursWithFraction)}ч в пути"

                                    if (allTicket.allTransfer == true) {
                                        flightTimeString += "/Без пересадок"
                                    }

                                    if (allTicket.allBadge != null) {
                                        binding.secondBadge.visibility = View.VISIBLE
                                        binding.secondBadge.text = allTicket.allBadge
                                    }
                                    binding.secondPrice.text = allTicket.allPrice.allValue.toString() + " ₽"
                                    binding.secondFlightStart.text = depTime
                                    binding.secondAirportStart.text = allTicket.allDeparture.allDepAirport
                                    binding.secondFlightEnd.text = arrTime
                                    binding.secondAirportEnd.text = allTicket.allArrival.allArrAirport
                                    binding.secondAddText.text = flightTimeString
                                    binding.secondLayout.visibility = View.VISIBLE
                                }

                                103 -> {
                                    val depDate = allTicket.allDeparture.allDepDate
                                    val depParsedDate = LocalDateTime.parse(depDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val depTime = LocalDateTime.parse(depDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val arrDate = allTicket.allArrival.allArrDate
                                    val arrParsedDate = LocalDateTime.parse(arrDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val arrTime = LocalDateTime.parse(arrDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val flightTime = Duration.between(depParsedDate, arrParsedDate)
                                    val hours = flightTime.toHours()
                                    val minutes = flightTime.toMinutes() % 60
                                    val hoursWithFraction = hours + (minutes / 60.0)
                                    var flightTimeString = "${String.format("%.1f", hoursWithFraction)}ч в пути"

                                    if (allTicket.allTransfer == true) {
                                        flightTimeString += "/Без пересадок"
                                    }

                                    if (allTicket.allBadge != null) {
                                        binding.thirdBadge.visibility = View.VISIBLE
                                        binding.thirdBadge.text = allTicket.allBadge
                                    }
                                    binding.thirdPrice.text = allTicket.allPrice.allValue.toString() + " ₽"
                                    binding.thirdFlightStart.text = depTime
                                    binding.thirdAirportStart.text = allTicket.allDeparture.allDepAirport
                                    binding.thirdFlightEnd.text = arrTime
                                    binding.thirdAirportEnd.text = allTicket.allArrival.allArrAirport
                                    binding.thirdAddText.text = flightTimeString
                                    binding.thirdLayout.visibility = View.VISIBLE
                                }

                                104 -> {
                                    val depDate = allTicket.allDeparture.allDepDate
                                    val depParsedDate = LocalDateTime.parse(depDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val depTime = LocalDateTime.parse(depDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val arrDate = allTicket.allArrival.allArrDate
                                    val arrParsedDate = LocalDateTime.parse(arrDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val arrTime = LocalDateTime.parse(arrDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val flightTime = Duration.between(depParsedDate, arrParsedDate)
                                    val hours = flightTime.toHours()
                                    val minutes = flightTime.toMinutes() % 60
                                    val hoursWithFraction = hours + (minutes / 60.0)
                                    var flightTimeString = "${String.format("%.1f", hoursWithFraction)}ч в пути"

                                    if (allTicket.allTransfer == true) {
                                        flightTimeString += "/Без пересадок"
                                    }

                                    if (allTicket.allBadge != null) {
                                        binding.fourBadge.visibility = View.VISIBLE
                                        binding.fourBadge.text = allTicket.allBadge
                                    }
                                    binding.fourPrice.text = allTicket.allPrice.allValue.toString() + " ₽"
                                    binding.fourFlightStart.text = depTime
                                    binding.fourAirportStart.text = allTicket.allDeparture.allDepAirport
                                    binding.fourFlightEnd.text = arrTime
                                    binding.fourAirportEnd.text = allTicket.allArrival.allArrAirport
                                    binding.fourAddText.text = flightTimeString
                                    binding.fourLayout.visibility = View.VISIBLE
                                }

                                105 -> {
                                    val depDate = allTicket.allDeparture.allDepDate
                                    val depParsedDate = LocalDateTime.parse(depDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val depTime = LocalDateTime.parse(depDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val arrDate = allTicket.allArrival.allArrDate
                                    val arrParsedDate = LocalDateTime.parse(arrDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val arrTime = LocalDateTime.parse(arrDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val flightTime = Duration.between(depParsedDate, arrParsedDate)
                                    val hours = flightTime.toHours()
                                    val minutes = flightTime.toMinutes() % 60
                                    val hoursWithFraction = hours + (minutes / 60.0)
                                    var flightTimeString = "${String.format("%.1f", hoursWithFraction)}ч в пути"

                                    if (allTicket.allTransfer == true) {
                                        flightTimeString += "/Без пересадок"
                                    }

                                    if (allTicket.allBadge != null) {
                                        binding.fiveBadge.visibility = View.VISIBLE
                                        binding.fiveBadge.text = allTicket.allBadge
                                    }
                                    binding.fivePrice.text = allTicket.allPrice.allValue.toString() + " ₽"
                                    binding.fiveFlightStart.text = depTime
                                    binding.fiveAirportStart.text = allTicket.allDeparture.allDepAirport
                                    binding.fiveFlightEnd.text = arrTime
                                    binding.fiveAirportEnd.text = allTicket.allArrival.allArrAirport
                                    binding.fiveAddText.text = flightTimeString
                                    binding.fiveLayout.visibility = View.VISIBLE
                                }
                                106 -> {
                                    val depDate = allTicket.allDeparture.allDepDate
                                    val depParsedDate = LocalDateTime.parse(depDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val depTime = LocalDateTime.parse(depDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val arrDate = allTicket.allArrival.allArrDate
                                    val arrParsedDate = LocalDateTime.parse(arrDate, DateTimeFormatter.ISO_DATE_TIME)
                                    val arrTime = LocalDateTime.parse(arrDate).format(
                                        DateTimeFormatter.ofPattern("hh:mm"))

                                    val flightTime = Duration.between(depParsedDate, arrParsedDate)
                                    val hours = flightTime.toHours()
                                    val minutes = flightTime.toMinutes() % 60
                                    val hoursWithFraction = hours + (minutes / 60.0)
                                    var flightTimeString = "${String.format("%.1f", hoursWithFraction)}ч в пути"

                                    if (allTicket.allTransfer == true) {
                                        flightTimeString += "/Без пересадок"
                                    }

                                    if (allTicket.allBadge != null) {
                                        binding.sixBadge.visibility = View.VISIBLE
                                        binding.sixBadge.text = allTicket.allBadge
                                    }
                                    binding.sixPrice.text = allTicket.allPrice.allValue.toString() + " ₽"
                                    binding.sixFlightStart.text = depTime
                                    binding.sixAirportStart.text = allTicket.allDeparture.allDepAirport
                                    binding.sixFlightEnd.text = arrTime
                                    binding.sixAirportEnd.text = allTicket.allArrival.allArrAirport
                                    binding.sixAddText.text = flightTimeString
                                    binding.sixLayout.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun goBack(){
        binding.allTicketsBackButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.airticketsFrame, SearchFragment())
            fragmentTransaction.commit()
        }
    }
}