package com.example.sleepassiatantv04.playlistv02

import androidx.annotation.DrawableRes
import androidx.versionedparcelable.VersionedParcelize
import java.time.Duration

@VersionedParcelize

data class DataSong (
    val nameSong :String,
    val durationSong : String,
    @DrawableRes val imageSong :Int)

