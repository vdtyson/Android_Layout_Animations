package com.example.imageviewer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imageviewer.MainActivity.Companion.IMAGE_DATA
import kotlinx.android.synthetic.main.activity_fullscreen.*

class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        val imageData = intent.getSerializableExtra(IMAGE_DATA) as ImageData
        val image = imageData.uri
        fullscreen_imageview.setImageURI(Uri.parse(image))
    }
}
