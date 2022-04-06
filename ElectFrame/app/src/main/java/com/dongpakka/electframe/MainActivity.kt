package com.dongpakka.electframe

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    lateinit var permissionChecker: PermissionChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        permissionChecker = PermissionChecker(this, permissions)
        if (permissionChecker.check()) {
            init()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(permissionChecker.checkGranted(requestCode, permissions, grantResults)){
            init()

        }else {
            // 권한 획득 실패
            Toast.makeText(application, "권한획득 실패", Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    private fun init() {
        val mediaImage = MediaImage(this)
        val adapter = PhotoPagerAdapter(this, mediaImage.getAllPhotos())
        viewPager.adapter = adapter

        // 3초마다 자동으로 슬라이드
        timer(period=3000) {
            runOnUiThread{
                viewPager.currentItem = (viewPager.currentItem + 1) % adapter.itemCount
            }
        }
    }
}