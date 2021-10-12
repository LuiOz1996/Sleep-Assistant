package com.example.sleepassiatantv04.playlistv02

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.Player
import com.example.sleepassiatantv04.R

class AdultsPlaylist : AppCompatActivity(), CommonOnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adults_playlist)

        val adultsSong = DataAdults.getAdultsSong()
        val adapter = CommonAdapter(this,adultsSong,this)
        val recycle = findViewById<RecyclerView>(R.id.PlaylistAdults)
        recycle.adapter = adapter
    }

    override fun onClick(position: Int) {
        if (position == 0) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 50)
            intent.putExtra("isplaylist",5)
            startActivity(intent)
        }
        if (position == 1) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 51)
            intent.putExtra("isplaylist",5)
            startActivity(intent)
        }
        if (position == 2) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 52)
            intent.putExtra("isplaylist",5)
            startActivity(intent)
        }
        if (position == 3) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 53)
            intent.putExtra("isplaylist",5)
            startActivity(intent)
        }
        if (position == 4) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 54)
            intent.putExtra("isplaylist",5)
            startActivity(intent)
        }
        if (position == 5) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 55)
            intent.putExtra("isplaylist",5)
            startActivity(intent)
        }
    }
}