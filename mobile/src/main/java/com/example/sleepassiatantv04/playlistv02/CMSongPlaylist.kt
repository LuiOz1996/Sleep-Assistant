package com.example.sleepassiatantv04.playlistv02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.Player
import com.example.sleepassiatantv04.R

class CMSongPlaylist : AppCompatActivity() , CommonOnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cmsong_playlist)

        val allcm = DataCM.getCMSongs()
        val adapter = CommonAdapter(this,allcm,this)
        val recycle = findViewById<RecyclerView>(R.id.PlaylistCM)
        recycle.adapter = adapter

        val decoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(this,R.color.white)!!)
        recycle.addItemDecoration(decoration)
    }

    override fun onClick(position: Int) {
        if (position == 0) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1000)
            intent.putExtra("isplaylist",3)
            startActivity(intent)
        }
        if (position == 1) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1001)
            intent.putExtra("isplaylist",3)
            startActivity(intent)
        }
        if (position == 2) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1002)
            intent.putExtra("isplaylist",3)
            startActivity(intent)
        }
        if (position == 3) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1003)
            intent.putExtra("isplaylist",3)
            startActivity(intent)
        }
    }
}