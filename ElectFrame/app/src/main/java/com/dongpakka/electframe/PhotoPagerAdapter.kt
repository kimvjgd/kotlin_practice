package com.dongpakka.electframe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PhotoPagerAdapter(fa:FragmentActivity, val uriList: List<String>):FragmentStateAdapter(fa){
    override fun getItemCount() = uriList.size

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.newInstance(uriList[position])
    }
}