package com.seoplee.sunflower_study_seoplee.data

import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import io.reactivex.Flowable
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val db : AppDataBase
) : PlantRepository {
    override fun getPlants(): Flowable<List<Plant>> {
        return db.plantDao().getPlants()
    }
}