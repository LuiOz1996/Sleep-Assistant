package com.example.sleepassiatantv04.playlistv02

import com.example.sleepassiatantv04.R

object DataFT {
    fun getAllFT() : List<DataSong>{
        return listOf(
            DataSong("Charlie & Chocolate Factory","1:45", R.drawable.charlie_and_chocolate_factory),
            DataSong("Peter Pan","1:24", R.drawable.peter_pan),
            DataSong("Home Along","1:21", R.drawable.home_along)
        )
    }
}