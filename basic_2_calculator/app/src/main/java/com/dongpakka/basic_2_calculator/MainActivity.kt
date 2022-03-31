package com.dongpakka.basic_2_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnPlus.setOnClickListener {
            val first = firstNumber.text.toString()
            val second = secondNumber.text.toString()
            if(first.length == 0 || second.length == 0){
                // 메세지 출력하고
                    Log.w(TAG, "숫자를 입력해야 합니다.")
                return@setOnClickListener
            }
            val result = first.toInt() + second.toInt()
            txtResult.text = result.toString()
        }

        btnMinus.setOnClickListener {
            val first = firstNumber.text.toString()
            val second = secondNumber.text.toString()
            if(first.length == 0 || second.length == 0){
                // 메세지 출력하고
                Log.w(TAG, "숫자를 입력해야 합니다.")
                return@setOnClickListener
            }
            val result = first.toInt() - second.toInt()
            txtResult.text = result.toString()
        }
    }
}

