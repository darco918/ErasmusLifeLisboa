package com.example.easmuslifeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val menuBottom =
            findViewById<com.ismaeldivita.chipnavigation.ChipNavigationBar>(R.id.menu_bottom)
        menuBottom.setItemSelected(R.id.profile, true)
        val fragmentFirst = ProfileFragment()
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction().replace(
            R.id.linear_layout_container,
            fragmentFirst,
            fragmentFirst.javaClass.simpleName
        )
        transaction.commit()
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
                    val fragment = EventsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.linear_layout_container,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                }
                R.id.collection -> {
                    val fragment = AlertsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.linear_layout_container,
                            fragment,
                            fragment.javaClass.simpleName
                        )
                        .commit()
                }
            }
        }
    }
}