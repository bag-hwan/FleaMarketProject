package com.example.sportsproject

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.sportsproject.fragment.HomeFragment
import com.example.sportsproject.fragment.MapFragment
import com.example.sportsproject.fragment.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.security.MessageDigest


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomnav)

        loadFragment(HomeFragment())


        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->{
                    loadFragment(HomeFragment())
                    true
                }
                R.id.map->{
                    loadFragment(MapFragment())
                    true
                }
                R.id.myPage->{
                    loadFragment(MyPageFragment())
                    true
                }
                else -> false
            }
        }





    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }




}