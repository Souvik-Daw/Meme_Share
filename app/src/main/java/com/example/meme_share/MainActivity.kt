package com.example.meme_share

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    var qwert:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load();
    }
    fun load()
    {
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
               qwert=response.getString("url")
                Glide.with(this).load(qwert).into(image)
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            })

// Add the request to the RequestQueue.
        queue.add(JsonObjectRequest)
    }

    fun shareMeme(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"check out this meme $qwert")
        val chooser=Intent.createChooser(intent,"share with")
        startActivity(chooser)
    }
    fun nextMeme(view: View) {
        load()
    }
}