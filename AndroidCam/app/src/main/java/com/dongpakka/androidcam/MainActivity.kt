package com.dongpakka.androidcam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.niqdev.mjpeg.DisplayMode
import com.github.niqdev.mjpeg.Mjpeg
import com.github.niqdev.mjpeg.MjpegInputStream
import kotlinx.android.synthetic.main.activity_main.*


const val PUB_TOPIC = "iot/camera/angle"
const val SERVER_URI = "tcp://172.30.1.17:1883"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSnapshot.setOnClickListener {
            mjpeg.visibility = View.INVISIBLE
            imageView.visibility = View.VISIBLE
            val url = "http://172.30.1.59:8000/mjpeg/snapshot"
            Glide.with(this).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView)
        }
        btnStream.setOnClickListener {
            mjpeg.visibility = View.VISIBLE
            imageView.visibility = View.INVISIBLE
            Mjpeg.newInstance()
                .open("http://172.30.1.59:8000/mjpeg/stream/", 5)
                .subscribe { inputStream: MjpegInputStream? ->
                    mjpeg.setSource(inputStream!!)
                    mjpeg.setDisplayMode(DisplayMode.BEST_FIT)
                }
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, value: Int, fromUser: Boolean) {
                if(fromUser) {
                    txtAngle.text = "SeekBar 값 $value"
                    // SeekBar의 값을 Publish....
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
    override fun onPause() {
        super.onPause()
        if (mjpeg.isStreaming)
            mjpeg.stopPlayback()
    }
}