package com.example.testairtickets.fragments

import Classes.InputFilter
import Classes.DataContainer
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.testairtickets.SheetPlug
import com.example.testairtickets.R
import com.example.testairtickets.databinding.FragmentAirTicketsBinding
import com.example.testairtickets.databinding.SheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit.MusicAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AirTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAirTicketsBinding
    private lateinit var bottomSheetBinding: SheetLayoutBinding
    private lateinit var dialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAirTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fromData = DataContainer.getFromText(requireContext())
        binding.fromEditText.setText(fromData)

        dialog = BottomSheetDialog(requireContext())
        bottomSheetBinding = SheetLayoutBinding.inflate(layoutInflater)

        bottomSheetBinding.sheetFromEdit.filters = arrayOf(InputFilter())
        bottomSheetBinding.sheetWhereEdit.filters = arrayOf(InputFilter())
        binding.fromEditText.filters = arrayOf(InputFilter())

        musicTopsGetInfo()
        showBottomSheet(dialog, bottomSheetBinding.root)
        sheetButtons()
        popularPlacesButtons()
        textCheck()
    }

    private fun musicTopsGetInfo(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val musicAPI = retrofit.create(MusicAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = musicAPI.getMusic()

            if (response.isSuccessful){

                val musicStripes = response.body()

                if (musicStripes != null){
                    withContext(Dispatchers.Main){

                        for (musicStripe in musicStripes.offers){

                            when (musicStripe.id) {
                                1 -> {
                                    binding.musicAuthor1.text = musicStripe.title
                                    binding.musicPlace1.text = musicStripe.town
                                    binding.musicPrice1.text = "от " + musicStripe.price.value.toString() + " ₽"
                                    binding.musicPicture1.visibility = View.VISIBLE
                                    binding.musicAirplane1.visibility = View.VISIBLE
                                }

                                2 -> {
                                    binding.musicAuthor2.text = musicStripe.title
                                    binding.musicPlace2.text = musicStripe.town
                                    binding.musicPrice2.text = "от " + musicStripe.price.value.toString() + " ₽"
                                    binding.musicPicture2.visibility = View.VISIBLE
                                    binding.musicAirplane2.visibility = View.VISIBLE
                                }

                                3 -> {
                                    binding.musicAuthor3.text = musicStripe.title
                                    binding.musicPlace3.text = musicStripe.town
                                    binding.musicPrice3.text = "от " + musicStripe.price.value.toString() + " ₽"
                                    binding.musicPicture3.visibility = View.VISIBLE
                                    binding.musicAirplane3.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showBottomSheet(dialog: BottomSheetDialog, view: View) {

        binding.whereEditText.setOnClickListener{
            dialog.setContentView(view)
            dialog.show()

            val dataMove = binding.fromEditText.text.toString()
            bottomSheetBinding.sheetFromEdit.setText(dataMove)
        }
    }

    private fun sheetButtons(){
        bottomSheetBinding.hardMapButton.setOnClickListener {
            val intent = Intent(requireContext(), SheetPlug::class.java)
            startActivity(intent)
        }

        bottomSheetBinding.whereYouWantButton.setOnClickListener {
            bottomSheetBinding.sheetWhereEdit.setText("Куда угодно")
        }

        bottomSheetBinding.weekendButton.setOnClickListener {
            val intent = Intent(requireContext(), SheetPlug::class.java)
            startActivity(intent)
        }

        bottomSheetBinding.hotTicketsButton.setOnClickListener {
            val intent = Intent(requireContext(), SheetPlug::class.java)
            startActivity(intent)
        }

        bottomSheetBinding.cancelButton.setOnClickListener {
            bottomSheetBinding.sheetWhereEdit.text = null
            bottomSheetBinding.cancelButton
        }

        bottomSheetBinding.searchButton.setOnClickListener {
            val fromData = bottomSheetBinding.sheetFromEdit.text.toString().trim()
            val whereData = bottomSheetBinding.sheetWhereEdit.text.toString().trim()
            DataContainer.saveData(requireContext(), fromData, whereData)

            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.airticketsFrame, SearchFragment())
            fragmentTransaction.commit()
            dialog.hide()
        }
    }

    private fun textCheck() {
        bottomSheetBinding.sheetWhereEdit.addTextChangedListener { text ->
            bottomSheetBinding.cancelButton.isVisible = !text.isNullOrEmpty()
            bottomSheetBinding.searchButton.isVisible = !text.isNullOrEmpty()
        }
    }

    private fun popularPlacesButtons(){
        bottomSheetBinding.firstPopularLayout.setOnClickListener {
            bottomSheetBinding.sheetWhereEdit.setText("Стамбул")
        }

        bottomSheetBinding.secondPopularLayout.setOnClickListener {
            bottomSheetBinding.sheetWhereEdit.setText("Сочи")
        }

        bottomSheetBinding.thirdPopularLayout.setOnClickListener {
            bottomSheetBinding.sheetWhereEdit.setText("Пхукет")
        }
    }
}