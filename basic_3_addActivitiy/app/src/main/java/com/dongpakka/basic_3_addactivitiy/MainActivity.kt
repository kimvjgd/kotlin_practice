package com.dongpakka.basic_3_addactivitiy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object {
        const val TEXT_KEY = "TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnOk.setOnClickListener {
            val i = Intent(this, MainActivity3::class.java)
            i.putExtra(TEXT_KEY, "HelloWorld!!!")
            startActivity(i)
        }
    }
}