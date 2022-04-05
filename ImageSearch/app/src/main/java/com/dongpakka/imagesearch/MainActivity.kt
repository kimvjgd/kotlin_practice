package com.dongpakka.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.dongpakka.imagesearch.data.Document
import com.dongpakka.imagesearch.data.Image
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val imageList = mutableListOf<Document>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageListView.adapter = ImageListAdapter(imageList, ::onItemClick)
        imageListView.layoutManager = GridLayoutManager(this, 3)

        btnSearch.setOnClickListener {
            val keyword = editKeyword.text.toString()
            searchImage(keyword)
            editKeyword.setText("")
        }

        OpenWeather.getWeather("seoul"){
            val iconCode = it.weather[0].icon
            val iconUrl = "http://openweathermap.org/img/wn/$iconCode@2x.png"
            val weather = "${it.weather[0].description} 온도 ${it.main.temp}'C / 습도 ${it.main.humidity}%"

            Glide.with(this).load(iconUrl).into(weatherIcon)
            weatherText.text = weather
            Log.d("CurrentWeather",it.toString())
        }


    }


    private fun searchImage(keyword: String) {
        KakaoImageSearch.search(keyword) {
            imageList.addAll(it!!.documents)
            imageListView.adapter?.notifyDataSetChanged()
        }
    }
//        KakaoImageSearch.getService()
//            .requestSearchImage(keyword = keyword, page = 1)
//            .enqueue(object : Callback<Image> {
//                override fun onFailure(call: Call<Image>, t: Throwable) {
//                    Log.e("----", t.toString())
//                }
//
//                override fun onResponse(call: Call<Image>, response: Response<Image>) {
//                    if (response.isSuccessful) {
//                        val image = response.body()
//                        imageList.addAll(image!!.documents)
//                        imageListView.adapter?.notifyDataSetChanged()
//                    }
//                }
//            })
    fun onItemClick(doc: Document) {

    }
}



