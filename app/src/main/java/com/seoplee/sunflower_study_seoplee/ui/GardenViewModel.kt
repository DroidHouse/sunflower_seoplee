package com.seoplee.sunflower_study_seoplee.ui

import androidx.lifecycle.ViewModel
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import javax.inject.Inject

@HiltViewModel
class GardenViewModel @Inject constructor(
    private val plantRepository: PlantRepository
    ) : ViewModel() {

    fun test(): Flowable<List<Plant>> {
        return plantRepository.getPlants()
    }
}