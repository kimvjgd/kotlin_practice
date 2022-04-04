package com.dongpakka.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main_detail.*
import kotlinx.android.synthetic.main.fragment_main_data.*
import kotlinx.android.synthetic.main.item_main.*
import kotlin.concurrent.timer

class MainDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_detail)
//
//        val data = intent.getParcelableExtra<MainData>("MAIN_DATA")
//        txtTitle.text = data?.title
//        txtContent.text = data?.content
//        Glide.with(this).load(data!!.image).into(imageViewDetail)
//
//        btnBack.setOnClickListener {
//            finish()
//        }
        val position = intent.getIntExtra("POSITION", 0)
        val adapter = MainDataPagerAdapter(this, MainDatas.items)
        viewPager.adapter = adapter
        viewPager.currentItem = position
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        timer(period = 3000) {
            runOnUiThread {
                viewPager.currentItem = (viewPager.currentItem + 1) % MainDatas.items.size
            }
        }
    }

}