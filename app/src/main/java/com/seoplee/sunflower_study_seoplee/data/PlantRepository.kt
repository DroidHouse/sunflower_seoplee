package com.seoplee.sunflower_study_seoplee.data

import io.reactivex.Flowable

interface PlantRepository {

    fun getPlants() : Flowable<List<Plant>>
}