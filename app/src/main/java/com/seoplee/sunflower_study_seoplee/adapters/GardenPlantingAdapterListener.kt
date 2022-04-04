package com.seoplee.sunflower_study_seoplee.adapters

import com.seoplee.sunflower_study_seoplee.data.GardenPlanting
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantAndGardenPlantings

interface GardenPlantingAdapterListener {

    fun onClickItem(item: PlantAndGardenPlantings)

}