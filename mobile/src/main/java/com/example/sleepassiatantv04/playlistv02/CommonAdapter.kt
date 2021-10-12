package com.example.sleepassiatantv04.playlistv02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.R

class CommonAdapter(Context : Context, private val songs : List<DataSong>,
                    private val onClickListener: CommonOnClickListener ) :
    RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(Context)
    override fun getItemCount(): Int = songs.size
    private fun getItem(position: Int):DataSong = songs[position]

    class ViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView){
        private val image : ImageView = itemView.findViewById(R.id.image_song)
        private val name : TextView = itemView.findViewById(R.id.name_song)
        private val duration:TextView = itemView.findViewById(R.id.duration_song)

        fun bind(version: DataSong){
            image.setImageResource(version.imageSong)
            name.text = version.nameSong
            duration.text = version.durationSong
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_for_playlist,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position)
        }
    }
}

