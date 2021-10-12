package com.example.sleepassiatantv04

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.playlistv02.*

class ChoosePlaylist : AppCompatActivity(), PlaylistOnclickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_playlist)

        val allPlaylis = DataStoragePlaylist.getAllPlaylist()
        val adapter = PlaylistAdapter(this,allPlaylis,this)

        val recycle = findViewById<RecyclerView>(R.id.playlist)
        recycle.adapter = adapter

        val gridLayutManager = GridLayoutManager(applicationContext,2,LinearLayoutManager.VERTICAL,false)
        recycle.layoutManager = gridLayutManager


    }

    override fun onClick(position: Int) {
        if (position == 0){
            val IntentToASMRPlaylist = Intent(this, ASMRSongPlaylist::class.java)
            startActivity(IntentToASMRPlaylist)
        }
        if (position == 1){
            val IntentToFTPlaylist = Intent(this, FTSongPlaylist::class.java)
            startActivity(IntentToFTPlaylist)
        }
        if (position == 2){
            val IntentToCMPlaylist = Intent(this, CMSongPlaylist::class.java)
            startActivity(IntentToCMPlaylist)
        }
        if (position == 3){
            val IntentToAllTrackPlaylist = Intent(this, AllSongPlaylist::class.java)
            startActivity(IntentToAllTrackPlaylist)
        }
        if (position == 4){
            val intent = Intent(this,AdultsPlaylist::class.java)
            startActivity(intent)
        }
    }
}

