package com.dongpakka.basic_3_usingintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        btnSMS.setOnClickListener{
            val uri = Uri.parse("smsto: " + "01071164522")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "동현이 문자!!")
            startActivity(intent)
        }

        btnInternet.setOnClickListener {
            val uri = Uri.parse("http://vintageappmarket.tumblr.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnMap.setOnClickListener {
            val uri = Uri.parse("geo: 37.5310091, 127.0261659")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnMarket.setOnClickListener {
            val uri = Uri.parse("market://details?id=com.psw.morningcall")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}