package com.example.sleepassiatantv04.playlistv02

import com.example.sleepassiatantv04.R

object DataAdults {
    fun getAdultsSong() : List<DataSong>{
        return listOf(
            DataSong("Lee Felix","1:12", R.drawable.lee_felix),
            DataSong("Min Yoon Gi","1:47",R.drawable.min_yoon_gi),
            DataSong("Kim Tea Huyng","1:46",R.drawable.kim_tea_huyng),
            DataSong("Kim Nam Joon","2:44",R.drawable.kim_nam_joon),
            DataSong("Park Ji Min","0:45",R.drawable.park_chi_min),
            DataSong("Jeon Jongguk","1:46",R.drawable.jeon_jongguk)
        )
    }
}