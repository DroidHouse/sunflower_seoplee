package com.seoplee.sunflower_study_seoplee.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface GardenPlantingRepository {

    fun createGardenPlanting(plantId: String) : Completable

    fun getPlantedGardens() : Single<List<PlantAndGardenPlantings>>

    fun isPlanted(plantId: String) : Flowable<Boolean>
}