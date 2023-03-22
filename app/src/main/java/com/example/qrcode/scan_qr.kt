package com.example.qrcode

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


class scan_qr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_qr)

        val sharedPreferences=getSharedPreferences("KEY_DISABLE_AUTO_ORIENTATION", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()

        val btnscan=findViewById<Button>(R.id.button)
        btnscan.setOnClickListener {
            editor.apply {
                putBoolean("KEY_DISABLE_AUTO_ORIENTATION", true)
                apply()
            }
            scanQR()
        }
    }

    private fun scanQR() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a barcode")
        options.setCameraId(0) // Use a specific camera of the device
        options.setBeepEnabled(true)
        options.setBarcodeImageEnabled(true)
        options.captureActivity = (CaptureAct::class.java)
        barcodeLauncher.launch(ScanOptions())

    }

    private val barcodeLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                .show()
        }
    }


}


class CaptureAct: CaptureActivity(){
}



