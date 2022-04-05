package com.dongpakka.imagesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dongpakka.imagesearch.data.Document


class ImageListAdapter(
    val imageList: MutableList<Document>,
    val onItemClick: (doc: Document) -> Unit) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    inner class ViewHolder(val imageView: ImageView) :
        RecyclerView.ViewHolder(imageView) {
        fun bind(doc: Document) {
            Glide.with(imageView).load(doc.thumbnailUrl).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(view as ImageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {          // 사용할 viewHolder를 보여주고 데이터의 index를 들고 있다.
        val data = imageList[position]
        holder.bind(data)
        holder.imageView.setOnClickListener {
            onItemClick(data)
        }
    }

    override fun getItemCount(): Int = imageList.size
}