package com.example.sleepassiatantv04.playlistv02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.Player
import com.example.sleepassiatantv04.R

class AllSongPlaylist : AppCompatActivity(), CommonOnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_song_playlist)

        val allsong = DataAllSong.getAllSong()
        val adapter = CommonAdapter(this,allsong,this)
        val recycle = findViewById<RecyclerView>(R.id.PlaylistAllSong)
        recycle.adapter = adapter

        val decoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(this,R.color.white)!!)
        recycle.addItemDecoration(decoration)
    }

    override fun onClick(position: Int) {
        if (position == 0) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 0)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 1) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 2) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 2)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 3) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 3)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 4) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 4)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 5) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 5)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 6) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 100)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 7) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 101)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 8) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 102)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 9) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1000)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 10) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1001)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 11) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1002)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
        if (position == 12) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1003)
            intent.putExtra("isplaylist",4)
            startActivity(intent)
        }
    }
}