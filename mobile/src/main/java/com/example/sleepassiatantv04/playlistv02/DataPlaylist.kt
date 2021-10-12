package com.example.sleepassiatantv04.playlistv02

import androidx.annotation.DrawableRes
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class DataPlaylist (
    val namePlaylist : String,
    @DrawableRes val imagePlaylist :Int)