package com.dongpakka.basic_3_addactivitiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val text = intent.getStringExtra(MainActivity.TEXT_KEY)
        textView2.text = text
    }
}