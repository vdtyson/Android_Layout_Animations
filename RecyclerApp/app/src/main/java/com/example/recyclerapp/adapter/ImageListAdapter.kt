package com.example.recyclerapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapp.R
import com.example.recyclerapp.model.ImageData
import kotlinx.android.synthetic.main.image_item_layout.view.*

class ImageListAdapter(val data: MutableList<ImageData>): RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.iv
        val name: TextView = view.image_name
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.image_item_layout, parent, false)
        ViewHolder(viewGroup)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageURI(Uri.parse(data[position].uri))
        holder.name.text = data[position].uri
    }
}