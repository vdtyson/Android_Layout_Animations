package com.example.sprint2.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sprint2.ui.MainActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint2.R
import com.example.sprint2.model.FoodItem
import com.example.sprint2.ui.DetailActivity
import com.example.sprint2.ui.selectedList
import kotlinx.android.synthetic.main.example_food_item_layout.view.*
import java.net.URI
import java.security.AccessController.getContext

class FoodListAdapter(val data: ArrayList<FoodItem>): RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {
    private var context: Context? = null
    private var lastPosition = -1

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.cv_iv
        val nameTextView = view.cv_tv as TextView
        val switch: Switch = view.cv_switch
        val cardView = view.cv as CardView
    }

    fun setEnterAnimation(viewToAnimate: View, position: Int) {
        if(position > lastPosition) {


            val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
       val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.example_food_item_layout, parent, false)
        ViewHolder(viewGroup)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(data[position].Icon())
        val formattedName = data[position].Name().replace("_"," ").replace("^A-Za-z","")
        holder.nameTextView.text = formattedName

        holder.switch.setOnClickListener {

            if(!selectedList.contains(formattedName) && holder.switch.isChecked) {
                selectedList.add(formattedName)
                println(selectedList)

                Toast.makeText(holder.cardView.context, "${holder.nameTextView.text} added to shopping list!",Toast.LENGTH_SHORT).show()
            } else {
                if(selectedList.contains(formattedName) && !holder.switch.isChecked) {
                    selectedList.remove(formattedName)
                Toast.makeText(holder.cardView.context, "${formattedName} removed from shopping list!", Toast.LENGTH_SHORT).show()
                }
                println(selectedList)
            }
        }
        var imageResource = data[position].Icon()
        holder.cardView.setOnClickListener {p0 ->
            var detailIntent = Intent(context, DetailActivity::class.java)
            detailIntent.putExtra("IMAGE_RESOURCE",imageResource)
            detailIntent.putExtra("IMAGE_NAME",formattedName)
            startActivity(holder.cardView.context, detailIntent, detailIntent.extras)

        }
        setEnterAnimation(holder.cardView, position)
    }
}