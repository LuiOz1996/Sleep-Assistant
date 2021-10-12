package com.example.sleepassiatantv04.playlistv02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.Player
import com.example.sleepassiatantv04.R

class ASMRSongPlaylist : AppCompatActivity(), CommonOnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asmrsong_playlist)

        val allasmr = DataASMR.getASMR()
        val adapter = CommonAdapter(this,allasmr,this)
        val recycle = findViewById<RecyclerView>(R.id.PlaylistASMR)
        recycle.adapter = adapter

        val decoration = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        decoration.setDrawable(ContextCompat.getDrawable(this,R.color.white)!!)
        recycle.addItemDecoration(decoration)
    }

    override fun onClick(position: Int) {
        if (position == 0) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 0)
            intent.putExtra("isplaylist",1)
            startActivity(intent)
        }
        if (position == 1) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 1)
            intent.putExtra("isplaylist",1)
            startActivity(intent)
        }
        if (position == 2) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 2)
            intent.putExtra("isplaylist",1)
            startActivity(intent)
        }
        if (position == 3) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 3)
            intent.putExtra("isplaylist",1)
            startActivity(intent)
        }
        if (position == 4) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 4)
            intent.putExtra("isplaylist",1)
            startActivity(intent)
        }
        if (position == 5) {
            val intent = Intent(this, Player::class.java)
            intent.putExtra("flag", 5)
            intent.putExtra("isplaylist",1)
            startActivity(intent)
        }
    }
}

