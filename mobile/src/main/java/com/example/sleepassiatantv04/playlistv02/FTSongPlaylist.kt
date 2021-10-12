package com.example.sleepassiatantv04.playlistv02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.Player
import com.example.sleepassiatantv04.R

class FTSongPlaylist : AppCompatActivity() , CommonOnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ftsong_playlist)

        val allft = DataFT.getAllFT()
        val adapter = CommonAdapter(this,allft,this)
        val recycle = findViewById<RecyclerView>(R.id.PlaylistFT)
        recycle.adapter = adapter

        val decoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(this,R.color.white)!!)
        recycle.addItemDecoration(decoration)
    }

    override fun onClick(position: Int) {
        if (position == 0) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 100)
            intent.putExtra("isplaylist",2)
            startActivity(intent)
        }
        if (position == 1) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 101)
            intent.putExtra("isplaylist",2)
            startActivity(intent)
        }
        if (position == 2) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 102)
            intent.putExtra("isplaylist",2)
            startActivity(intent)
        }
    }
}