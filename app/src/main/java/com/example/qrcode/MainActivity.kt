package com.example.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var QRcode : ImageView
    private lateinit var getData: EditText
    private lateinit var btngetQRcode : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QRcode=findViewById(R.id.QRcode)
        getData=findViewById(R.id.getData)
        btngetQRcode=findViewById(R.id.generateQRcode)

        btngetQRcode.setOnClickListener{

            val data= getData.text.toString().trim()

            if(data.isEmpty()){
                Toast.makeText(this, "Account not found, please register!", Toast.LENGTH_SHORT).show()
            } else {
                val writer= QRCodeWriter()
                try{

                    val bitMatrix= writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                    val width= bitMatrix.width
                    val height=bitMatrix.height
                    val bmp= Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for(x in 0 until width){
                        for(y in 0 until height){
                            bmp.setPixel(x,y, if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                        }
                    }
                    QRcode.setImageBitmap(bmp)
                } catch(e: WriterException) {
                    e.printStackTrace()
                }
            }
        }

    }
}
/**
 *
 */