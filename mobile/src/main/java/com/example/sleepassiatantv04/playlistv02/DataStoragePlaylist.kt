package com.example.sleepassiatantv04.playlistv02

import com.example.sleepassiatantv04.R

object DataStoragePlaylist {
    fun getAllPlaylist () : List<DataPlaylist>{
        return listOf(
            DataPlaylist("ASMR", R.drawable.icons_playlist),
            DataPlaylist("Fairy Tales",R.drawable.icons_playlist),
            DataPlaylist("Classical music",R.drawable.icons_playlist),
            DataPlaylist("All track",R.drawable.icons_playlist),
            DataPlaylist("Hot Hot",R.drawable.icons_playlist)
        )
    }
}
