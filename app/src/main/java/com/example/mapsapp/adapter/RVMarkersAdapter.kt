package com.example.mapsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mapsapp.databinding.RvMarkersItemBinding
import com.example.mapsapp.domain.Marker

class RVMarkersAdapter : RecyclerView.Adapter<RVMarkersAdapter.MarkerViewHolder>() {

    val list = mutableListOf<Marker>()
    var clickListener: ((marker: Marker) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MarkerViewHolder(
        RvMarkersItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        holder.apply {
            setData(list[position])
            itemView.setOnClickListener {
                clickListener?.invoke(list[position])
            }
        }
    }

    override fun getItemCount() = list.size

    class MarkerViewHolder(private val binding: RvMarkersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(marker: Marker) {
            with(binding) {
                tvTitle.text = marker.title
                tvLat.text = marker.lat.toString()
                tvLng.text = marker.lng.toString()
            }
        }
    }
}