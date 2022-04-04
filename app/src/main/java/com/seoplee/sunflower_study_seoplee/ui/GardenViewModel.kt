package com.seoplee.sunflower_study_seoplee.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoplee.sunflower_study_seoplee.data.GardenPlantingRepository
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantAndGardenPlantings
import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class GardenViewModel @Inject constructor(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    private val _gardenPlantings = MutableLiveData<List<PlantAndGardenPlantings>>()
    val gardenPlantings : LiveData<List<PlantAndGardenPlantings>>
        get() = _gardenPlantings

    @SuppressLint("CheckResult")
    fun fetchData() {
        gardenPlantingRepository.getPlantedGardens()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it -> _gardenPlantings.postValue(it) }
    }
}




