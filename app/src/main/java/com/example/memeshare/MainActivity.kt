package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var CurrentImageurl:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadimage()
    }

    private fun loadimage() {
        progress.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = " https://meme-api.herokuapp.com/gimme"
// Request a string response from the provided URL.
        val JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    CurrentImageurl = response.getString("url")
                    Glide.with(this).load( CurrentImageurl).listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progress.visibility=View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progress.visibility=View.GONE
                            return false
                        }
                    }).into(memeImageView)
                },
                { })

// Add the request to the RequestQueue.
        queue.add(JsonObjectRequest)}
        fun sharememe(view: View) {
            var mediaPlayer = MediaPlayer.create(this,R.raw.hii)
            mediaPlayer.start()
          val intent = Intent(Intent.ACTION_SEND)
            intent.type= "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"hey checkout this $CurrentImageurl")
            val chooser= Intent.createChooser(intent,"share this by using")
            startActivity(chooser)
        }


        fun Nextmeme(view: View) {
            var mediaPlayer = MediaPlayer.create(this,R.raw.hii)
             mediaPlayer.start()
            loadimage()
        }

    }

