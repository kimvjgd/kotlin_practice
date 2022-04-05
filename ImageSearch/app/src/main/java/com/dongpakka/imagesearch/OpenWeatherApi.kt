package com.dongpakka.imagesearch

import android.util.Log
import com.dongpakka.imagesearch.data.weather.CurrentWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

// http://api.openweathermap.org/data/2.5/weather
// ?q=seoul&APPID=<api key>&lang=kr&units=metric

const val APPID = "73594ac2d49706895467644d288b2f19"

interface OpenWeatherService {
    @GET("/data/2.5/weather?APPID=$APPID")
    fun getWeather(
        @Query("q") city: String,
        @Query("lang") lang: String = "kr",
        @Query("units") units: String = "metric"
    ): Call<CurrentWeather>
}

object OpenWeather {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(OpenWeatherService::class.java)

    fun getWeather(city: String, lang: String="kr", units: String="metric", callback: (CurrentWeather)->Unit){
        service.getWeather(city, lang, units)
            .enqueue(object: Callback<CurrentWeather> {
                override fun onResponse(
                    call: Call<CurrentWeather>,
                    response: Response<CurrentWeather>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()
                        callback(data!!)
                    }
                }

                override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                    Log.e("-------", t.toString())
                }
            })
    }
}