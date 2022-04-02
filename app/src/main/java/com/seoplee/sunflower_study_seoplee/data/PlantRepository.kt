package com.seoplee.sunflower_study_seoplee.data

import io.reactivex.Flowable
import io.reactivex.Single

interface PlantRepository {

    fun getPlants() : Single<List<Plant>>
}