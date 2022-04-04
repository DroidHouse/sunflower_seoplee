package com.seoplee.sunflower_study_seoplee.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoplee.sunflower_study_seoplee.data.GardenPlantingRepository
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantAndGardenPlantings
import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class GardenViewModel @Inject constructor(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    fun fetchData() : Single<List<PlantAndGardenPlantings>> {
        return gardenPlantingRepository.getPlantedGardens()
    }
}




