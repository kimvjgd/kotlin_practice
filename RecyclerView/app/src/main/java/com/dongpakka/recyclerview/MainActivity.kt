package com.dongpakka.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_detail.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_main_list.adapter = MainAdapter(MainDatas.items, ::onItemClick)
        rv_main_list.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val title = edtTitle.text.toString()
            val content = edtContent.text.toString()
            val item = MainData(title, content,"")
            MainDatas.items += item

            rv_main_list.adapter?.notifyDataSetChanged()

            edtTitle.setText("")
            edtContent.setText("")
        }


    }

    // R.V. 아이템을 클릭했을 때 호출할 메서드
    fun onItemClick(position: Int) {
//        val data= MainDatas.items[position]
//        Toast.makeText(application, "${data.title}, ${data.content}", Toast.LENGTH_LONG).show()
        val i = Intent(this, MainDetailActivity::class.java)
        i.putExtra("POSITION", position)


        startActivity(i)
    }
}