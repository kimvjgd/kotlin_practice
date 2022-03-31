package com.dongpakka.basic_1_usingxml

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)      // inflate를 처리해 줌

        txtHello.apply{
            text = "안녕하세요"
            textSize = 32.0F            // Double -> Float
            setTextColor(Color.parseColor("#FF0000"))
        }



//        findViewById<TextView>(R.id.txtHello).apply{
//            text = "안녕하세요"
//            textSize = 32.0F            // Double -> Float
//            setTextColor(Color.parseColor("#FF0000"))
//        }


    }
}