package com.seoplee.sunflower_study_seoplee.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seoplee.sunflower_study_seoplee.R
import com.seoplee.sunflower_study_seoplee.data.GardenPlanting
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantAndGardenPlantings
import com.seoplee.sunflower_study_seoplee.databinding.ViewholderGardenPlantingBinding


class GardenPlantingAdapter(
    private val adapterListener : GardenPlantingAdapterListener
) : ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(
    GardenPlantDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.viewholder_garden_planting,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gardenPlant = getItem(position)
        holder.bind(gardenPlant)
        holder.bindAdapter(gardenPlant, adapterListener)
    }

    class ViewHolder(
        private val binding: ViewholderGardenPlantingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlantAndGardenPlantings) {
            binding.apply {
                plantAndGardenPlantings = item
                executePendingBindings()
            }
        }
        fun bindAdapter(item: PlantAndGardenPlantings, adapterListener: GardenPlantingAdapterListener) {
            binding.setOnClickListener {
                adapterListener.onClickItem(item)
            }
        }
    }
}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {

    override fun areItemsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
        return oldItem.plant == newItem.plant
    }
}