package com.seoplee.sunflower_study_seoplee.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.databinding.ViewholderPlantListBinding


class PlantAdapter(
    private val adapterListener : PlantAdapterListener
) : ListAdapter<Plant, PlantAdapter.PlantListViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListViewHolder {
        return PlantListViewHolder(
            ViewholderPlantListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(plant)
        holder.bindAdapter(plant, adapterListener)
    }

    class PlantListViewHolder(
        private val binding: ViewholderPlantListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant) {
            binding.apply {
                plant = item
                executePendingBindings()
            }
        }
        fun bindAdapter(item: Plant, adapterListener: PlantAdapterListener) {
            binding.setOnClickListener {
                adapterListener.onClickItem(item)
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {

    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}