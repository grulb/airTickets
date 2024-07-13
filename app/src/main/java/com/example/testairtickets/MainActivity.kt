package com.example.testairtickets

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.testairtickets.databinding.ActivityMainBinding
import com.example.testairtickets.fragments.AirTicketsFragment
import com.example.testairtickets.fragments.HotelsFragment
import com.example.testairtickets.fragments.MapsFragment
import com.example.testairtickets.fragments.ProfileFragment
import com.example.testairtickets.fragments.SubscribesFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        replaceFragment(AirTicketsFragment())
        changeFragment()
    }

    private fun changeFragment(){
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.airticketsitem -> replaceFragment(AirTicketsFragment())
                R.id.hotelitem -> replaceFragment(HotelsFragment())
                R.id.mapitem -> replaceFragment(MapsFragment())
                R.id.subscribesitem -> replaceFragment(SubscribesFragment())
                R.id.profileitem -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.airticketsFrame, fragment)
        fragmentTransaction.commit()
    }
}