package com.example.sprint2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sprint2.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val foodImage = intent.getSerializableExtra("IMAGE_RESOURCE") as Int
        val foodName = intent.getSerializableExtra("IMAGE_NAME") as String
        detail_iv.setImageResource(foodImage)
        detail_tv.text = foodName

    }
}
