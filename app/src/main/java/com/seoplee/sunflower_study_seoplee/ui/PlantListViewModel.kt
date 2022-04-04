package com.seoplee.sunflower_study_seoplee.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _plantList = MutableLiveData<List<Plant>>()
    val plantList : LiveData<List<Plant>>
        get() = _plantList

    @SuppressLint("CheckResult")
    fun fetchData() {
        plantRepository.getPlants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { data -> _plantList.postValue(data) },
                { Log.e(TAG, "loadPlantList Error : ${it.message}")}
            )
    }

    companion object {
        const val TAG = "PlantListViewModel"
    }
}

