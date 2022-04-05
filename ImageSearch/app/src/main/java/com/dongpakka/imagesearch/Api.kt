package com.dongpakka.imagesearch

import android.util.Log
import com.dongpakka.imagesearch.data.Image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoImageSearchService {
    @Headers("Authorization: KakaoAK a903569250932f8162a22f5de3258097")
    @GET("/v2/search/image")
    fun requestSearchImage(
        @Query("query") keyword: String,
        @Query("sort") sort: String = "recency",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80
    ): Call<Image>
}

object KakaoImageSearch {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(KakaoImageSearchService::class.java)
    fun search(keyword: String, sort:String="recency", page: Int=1, size:Int=60, callback: (Image)->Unit){
        service.requestSearchImage(keyword,sort,page, size)
            .enqueue(object : Callback<Image>{
                override fun onResponse(call: Call<Image>, response: Response<Image>) {
                    if(response.isSuccessful){
                        val image = response.body()
                        callback(image!!)
                    }
                }

                override fun onFailure(call: Call<Image>, t: Throwable) {
                    Log.e("--------", t.toString())
                }
            })
    }

}