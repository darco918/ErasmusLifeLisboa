package com.example.easmuslifeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        val scanButton = findViewById<Button>(R.id.scanButton)
        val searchUserButton = findViewById<Button>(R.id.searchButton)

        scanButton.setOnClickListener {
            startActivity(Intent(this, AdminScannerActivity::class.java))
        }

        searchUserButton.setOnClickListener {
            startActivity(Intent(this, ListOfUsersActivity::class.java))
        }
    }
}