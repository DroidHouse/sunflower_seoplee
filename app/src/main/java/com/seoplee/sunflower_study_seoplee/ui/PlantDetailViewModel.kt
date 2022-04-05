package com.seoplee.sunflower_study_seoplee.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.seoplee.sunflower_study_seoplee.BaseViewModel
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
    savedStateHandle: SavedStateHandle,
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository
) : BaseViewModel() {

    private val _plant = MutableLiveData<Plant>()
    val plant : LiveData<Plant>
        get() = _plant

    private val _isPlanted = MutableLiveData<Boolean>()
    val isPlanted : LiveData<Boolean>
        get() = _isPlanted

    // navigation에서 safeArgs 값을 넘겨줄 때 사용한 키를 이용해 savedStateHandle.get()으로 받아올 수 있다.
    val plantId: String= savedStateHandle.get<String>(PLANT_ID_SAVED_STATE_KEY)!!

    init {
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(
            plantRepository.getPlant(plantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data -> _plant.value = data },
                    { Log.e(TAG, "fetchData Error : ${it.message}")}
                )
        )

        compositeDisposable.add(
            gardenPlantingRepository.isPlanted(plantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { data -> _isPlanted.value = data },
                    { Log.e(TAG, "fetchData isPlanted Error : ${it.message}") }
                )
        )
    }

    fun addPlantToGarden() {
        compositeDisposable.add(
            gardenPlantingRepository.createGardenPlanting(plantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { Log.d(TAG,"addPlantToGarden Success") },
                    { Log.e(TAG,"addPlantToGarden Error : ${it.message}")}
                )
        )
    }

    companion object {
        const val TAG = "PlantDetailViewModel"

        private const val PLANT_ID_SAVED_STATE_KEY = "plantId"
    }
}
