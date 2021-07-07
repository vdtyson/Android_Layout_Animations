package com.example.recyclerapp.model

import android.net.Uri
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.toUri
import com.example.recyclerapp.R

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Fade()


        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)


        val uri = intent.getSerializableExtra("IMAGE_URI") as String
        detail_iv.setImageURI(Uri.parse(uri))
        detail_tv.text = uri
    }

}
