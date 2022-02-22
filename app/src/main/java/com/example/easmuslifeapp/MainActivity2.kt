package com.example.easmuslifeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.easmuslifeapp.AlertsFragment.Companion.newInstance

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val contor = intent.getIntExtra("id", 1)

        val menuBottom =
            findViewById<com.ismaeldivita.chipnavigation.ChipNavigationBar>(R.id.menu_bottom)
//        if(contor == 1) {
//            menuBottom.setItemSelected(R.id.profile, true)
//            val fragmentFirst = ProfileFragment()
//            val manager: FragmentManager = supportFragmentManager
//            val transaction: FragmentTransaction = manager.beginTransaction()
//            transaction.commit()
//        }
//
//        if(contor==2){
//            menuBottom.setItemSelected(R.id.profile, true)
//            val fragmentFirst = ProfileFragment.newInstance()
//            val manager: FragmentManager = supportFragmentManager
//            val transaction: FragmentTransaction = manager.beginTransaction()
//            transaction.commit()
//        }

        menuBottom.setOnItemSelectedListener { id ->

            when (id) {
                R.id.profile -> {
                    val fragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.linear_layout_container,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                }

                R.id.challenges -> {
                    val fragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .commit()
                }

                R.id.collection -> {
                    val fragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .commit()
                }

            }
        }


    }
}