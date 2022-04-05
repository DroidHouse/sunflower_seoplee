package com.seoplee.sunflower_study_seoplee.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.seoplee.sunflower_study_seoplee.data.PlantAndGardenPlantings
import com.seoplee.sunflower_study_seoplee.data.UnsplashPhoto
import com.seoplee.sunflower_study_seoplee.databinding.ViewholderGalleryBinding


class GalleryAdapter(
    private val adapterListener: GalleryAdapterListener
) : PagingDataAdapter<UnsplashPhoto, GalleryAdapter.GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ViewholderGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
            holder.bindAdapter(photo, adapterListener)
        }
    }

    class GalleryViewHolder(
        private val binding: ViewholderGalleryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnsplashPhoto) {
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
        fun bindAdapter(item: UnsplashPhoto, adapterListener: GalleryAdapterListener) {
            binding.root.setOnClickListener {
                adapterListener.onClickItem(item)
            }
        }
    }
}

private class GalleryDiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
    override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem == newItem
    }
}
