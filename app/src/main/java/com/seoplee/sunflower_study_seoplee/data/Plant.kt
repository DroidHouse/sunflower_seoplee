package com.seoplee.sunflower_study_seoplee.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plant (
    @PrimaryKey val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
)