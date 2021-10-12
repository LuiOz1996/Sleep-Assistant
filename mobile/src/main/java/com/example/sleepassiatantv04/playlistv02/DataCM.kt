package com.example.sleepassiatantv04.playlistv02

import com.example.sleepassiatantv04.R

object DataCM {
    fun getCMSongs () : List<DataSong>{
        return listOf(
            DataSong("Betkhoven Moonlight Sonata","5:31", R.drawable.betkhoven_moonlight_sonata),
            DataSong("Edvard Grig Final Per Gyunt","3:12", R.drawable.edvard_grig_final_per_gyunt),
            DataSong("Hans Zimmer Hero","4:49", R.drawable.hans_zimmer_hero),
            DataSong("Japanese sakura","9:30", R.drawable.japanese_sakura)
        )
    }
}