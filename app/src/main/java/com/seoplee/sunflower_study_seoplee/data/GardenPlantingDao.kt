package com.seoplee.sunflower_study_seoplee.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenPlantingDao {

    @Insert
    fun insertGardenPlanting(gardenPlanting: GardenPlanting): Completable

    @Transaction
    @Query("SELECT * FROM Plant WHERE plant_id IN (SELECT DISTINCT(garden_plant_id) FROM GardenPlanting)")
    fun getPlantedGardens(): Single<List<PlantAndGardenPlantings>>

    @Query("SELECT EXISTS(SELECT 1 FROM GardenPlanting WHERE garden_plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: String): Flowable<Boolean>


}