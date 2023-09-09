package com.o7services.musicplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.o7services.musicplayer.databinding.RecyclerviewBinding

class RecyclerAdapter(var musicContent: ArrayList<MusicContent>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(var view: RecyclerviewBinding): RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = RecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = musicContent.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.musicContent = musicContent[position]
    }
}