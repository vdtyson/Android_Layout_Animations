package com.example.recyclerapp.ui

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapp.R
import com.example.recyclerapp.adapter.ImageListAdapter
import com.example.recyclerapp.model.ImageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    companion object {
        const val IMAGE_REQUEST_CODE = 0
    }

    var imgList: ArrayList<ImageData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        get_image_button.setOnClickListener {
            val intent = Intent(ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageURI = data?.data
            if (imageURI != null) {
                list_view.removeAllViews()
                imgList.add(ImageData(imageURI.toString()))
                list_view.setHasFixedSize(true)
                val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                val adapter = ImageListAdapter(imgList)
                list_view.layoutManager = manager
                list_view.adapter = adapter
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
