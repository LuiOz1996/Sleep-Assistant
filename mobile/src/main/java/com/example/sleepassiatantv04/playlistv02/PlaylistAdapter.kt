package com.example.sleepassiatantv04.playlistv02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sleepassiatantv04.R

class PlaylistAdapter(Context: Context, private val playlistList : List<DataPlaylist>,
                      private val onClickListener: PlaylistOnclickListener)
    : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(Context)
    override fun getItemCount(): Int = playlistList.size
    private fun getItem(position: Int):DataPlaylist = playlistList[position]

    class ViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView){
            private val image :ImageView = itemView.findViewById(R.id.image_playlist)
            private val name :TextView = itemView.findViewById(R.id.name_playlist)

            fun bind(version: DataPlaylist){
                image.setImageResource(version.imagePlaylist)
                name.text = version.namePlaylist
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_choose_playlist,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position)
        }
    }

}