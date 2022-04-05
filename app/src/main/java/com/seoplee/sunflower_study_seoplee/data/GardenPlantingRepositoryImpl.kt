package com.seoplee.sunflower_study_seoplee.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GardenPlantingRepositoryImpl @Inject constructor(
    private val gardenPlantingDao: GardenPlantingDao
) : GardenPlantingRepository {

    override fun createGardenPlanting(plantId: String): Completable {
        val gardenPlanting = GardenPlanting(plantId)
        return gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }

    override fun getPlantedGardens(): Single<List<PlantAndGardenPlantings>> {
        return gardenPlantingDao.getPlantedGardens()
    }

    override fun isPlanted(plantId: String): Flowable<Boolean> {
        return gardenPlantingDao.isPlanted(plantId)
    }
}