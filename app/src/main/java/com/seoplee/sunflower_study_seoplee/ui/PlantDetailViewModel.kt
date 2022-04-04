package com.seoplee.sunflower_study_seoplee.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoplee.sunflower_study_seoplee.data.GardenPlantingRepository
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import com.seoplee.sunflower_study_seoplee.workers.SeedDatabaseWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    lateinit var plantId: String

    @SuppressLint("CheckResult")
    fun addPlantToGarden(plantId: String) {
        gardenPlantingRepository.createGardenPlanting(plantId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d(TAG,"addPlantToGarden Success") },
                { Log.e(TAG,"addPlantToGarden Fail : ${it.message}")}
            )
    }

    companion object {
        const val TAG = "PlantDetailViewModel"
    }
}
