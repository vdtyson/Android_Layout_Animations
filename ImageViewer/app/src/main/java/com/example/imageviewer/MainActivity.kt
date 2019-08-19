package com.example.imageviewer

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.net.URI
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_REQUEST_CODE = 0
        const val IMAGE_DATA = "IMAGE_NAME"
    }

    var imgList: ArrayList<ImageData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_get_image.setOnClickListener {
            val intent = Intent(ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager)!= null) {
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageURI = data?.data
            if (imageURI != null) {
                imgList.add(ImageData(imageURI.toString()))
                populateImgData()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    // Creates text view to populate the scroll view with image text
    private fun createTextView(imageData: ImageData, index: Int): TextView {
        val view = TextView(this)

        view.id = index
        view.text = "${imageData.uri}"
        view.textSize = 24f
        view.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)

            intent.putExtra(IMAGE_DATA, imageData)

            startActivity(intent)
        }

        return view
    }

    // populateImgData function populates the list of images by adding their names and index from imgList
    fun populateImgData() {
        for(i in 0 until imgList.size) {
            linear_scroll.addView(createTextView(imgList[i], i))

        }
    }
}

class ImageData(val uri: String): Serializable
