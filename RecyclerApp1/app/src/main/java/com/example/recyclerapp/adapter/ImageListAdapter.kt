package com.example.recyclerapp.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapp.R
import com.example.recyclerapp.model.DetailActivity
import com.example.recyclerapp.model.ImageData
import com.example.recyclerapp.ui.MainActivity
import kotlinx.android.synthetic.main.image_item_layout.view.*

class ImageListAdapter(val data: MutableList<ImageData>): RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.iv
        val name: TextView = view.image_name
        val cardView: CardView = view.card_view
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
        holder.cardView.setOnClickListener {view ->
            val recyclerIntent = Intent(view.context, DetailActivity::class.java)

            recyclerIntent.putExtra("IMAGE_URI", data[position].uri)
            val optionsBundle: Bundle = ActivityOptions.makeSceneTransitionAnimation(view.context as Activity, holder.image, "shared_image").toBundle()

            view.context.startActivity(recyclerIntent, optionsBundle)
        }
        setEnterAnimation(holder.cardView, position)
    }

    fun setEnterAnimation(viewToAnimate: View, i: Int) {
        val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.fade_in)
        viewToAnimate.startAnimation(animation)
    }
}