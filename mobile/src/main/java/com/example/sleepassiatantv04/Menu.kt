package com.example.sleepassiatantv04

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button_connect_smart_watch.setOnClickListener{
            val Intent_to_connect_smatr_watch = Intent(this,ConnectSmartWatch::class.java)
            startActivity(Intent_to_connect_smatr_watch)
        }

        button_select_playlist.setOnClickListener{
            val Intent_to_select_playlist = Intent(this, ChoosePlaylist::class.java)
            startActivity(Intent_to_select_playlist)
        }
    }
}