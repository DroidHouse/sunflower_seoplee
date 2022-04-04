package com.seoplee.sunflower_study_seoplee.data

import androidx.room.*
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(entity = Plant::class, parentColumns = ["plant_id"], childColumns = ["garden_plant_id"])
    ]
)
data class GardenPlanting (
    @PrimaryKey @ColumnInfo(name = "garden_plant_id") val gardenPlantId: String,
    val plantDat: Calendar = Calendar.getInstance(),
    val lastWateringDat: Calendar = Calendar.getInstance()
)