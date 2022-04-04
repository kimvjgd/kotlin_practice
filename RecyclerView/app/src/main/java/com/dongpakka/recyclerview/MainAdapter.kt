package com.dongpakka.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_main.view.*





class MainAdapter(var items:MutableList<MainData>, val l: (Int) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>(){



    inner class MainViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_main_title
        val tvContent = itemView.tv_main_content
        val imageView = itemView.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)   // 데이터 하나 화면에 등장
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {          // 데이터를 연결한다.
        holder.itemView.setOnClickListener {
            l(position)
        }
        items[position].let { item ->               // 아이템하나하나 of items
            with(holder) {
                tvTitle.text = item.title
                tvContent.text = item.content
                Glide.with(itemView).load(item.image)
                    .override(64,64)
                    .into(imageView)
            }

        }
    }

    override fun getItemCount(): Int = items.size

}