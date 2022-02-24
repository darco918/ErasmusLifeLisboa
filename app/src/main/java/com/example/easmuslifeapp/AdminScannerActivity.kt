package com.example.easmuslifeapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.integration.android.IntentIntegrator

class AdminScannerActivity : AppCompatActivity() {
    private lateinit var mQrResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_scanner)

        mQrResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
                    if (result.contents != null) {
                        // Do something with the contents (this is usually a URL)
                        println(result.contents)
                        searchDataBaseForUser(result.contents)
                    }
                }
            }
        // Starts scanner on Create of Overlay (you can also call this function using a button click)
        startScanner()
    }

    private fun searchDataBaseForUser(uid: String) {
        startActivity(Intent(this, UserProfileActivity::class.java))
    }

    private fun startScanner() {
        val scanner = IntentIntegrator(this)
        // QR Code Format
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("QR Code Scanner Prompt Text")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())
    }
}