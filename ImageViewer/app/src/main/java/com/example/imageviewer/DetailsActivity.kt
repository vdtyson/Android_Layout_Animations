package com.example.imageviewer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imageviewer.MainActivity.Companion.IMAGE_DATA
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val imageData = intent.getSerializableExtra(IMAGE_DATA) as ImageData
        val image = imageData.uri

        image_view.setImageURI(Uri.parse(image))
        txt_name.setText(image)

        image_view.setOnClickListener {
            val fullscreenIntent = Intent(this, FullscreenActivity::class.java)

            fullscreenIntent.putExtra(IMAGE_DATA,imageData)

            startActivity(fullscreenIntent)
        }

    }
}


