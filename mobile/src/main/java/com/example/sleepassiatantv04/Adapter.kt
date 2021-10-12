package com.example.sleepassiatantv04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    Context: Context, private val divace: List<DataBluetooth>,
    private val onClickListener: ConnectSmartWatch
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(Context)
    override fun getItemCount(): Int = divace.size
    private fun getItem(position: Int): DataBluetooth = divace[position]

    class ViewHolder(itemView : View)
        : RecyclerView.ViewHolder(itemView){
        private val image : ImageView = itemView.findViewById(R.id.image_divace)
        private val name : TextView = itemView.findViewById(R.id.name_divace)

        fun bind(version: DataBluetooth){
            image.setImageResource(version.imageDivace)
            name.text = version.nameDivace
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_for_bluetooth,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position)
        }
    }
}