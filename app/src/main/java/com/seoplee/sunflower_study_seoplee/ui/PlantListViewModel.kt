package com.seoplee.sunflower_study_seoplee.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoplee.sunflower_study_seoplee.BaseViewModel
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : BaseViewModel() {

    private val _plantList = MutableLiveData<List<Plant>>()
    val plantList : LiveData<List<Plant>>
        get() = _plantList

    fun fetchData() {
        compositeDisposable.add(
            plantRepository.getPlants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data -> _plantList.postValue(data) },
                    { Log.e(TAG, "loadPlantList Error : ${it.message}") }
                )
        )
    }

    companion object {
        const val TAG = "PlantListViewModel"
    }
}

