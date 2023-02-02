package com.example.qrcode

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.scanbutton).setOnClickListener {
            startActivity(Intent(this,scan_qr::class.java))
        }
        findViewById<Button>(R.id.generatebutton).setOnClickListener {
            startActivity(Intent(this,generate_qr::class.java))
        }


        }

    }

