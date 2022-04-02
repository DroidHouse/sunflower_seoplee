package com.seoplee.sunflower_study_seoplee.data

import com.seoplee.sunflower_study_seoplee.data.PlantRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val db : AppDataBase
) : PlantRepository {
    override fun getPlants(): Single<List<Plant>> {
        return db.plantDao().getPlants()
    }
}