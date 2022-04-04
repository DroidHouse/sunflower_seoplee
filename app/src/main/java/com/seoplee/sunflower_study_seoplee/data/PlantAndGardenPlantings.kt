package com.seoplee.sunflower_study_seoplee.data

import androidx.room.Embedded
import androidx.room.Relation


/**
 * Embedded, Relation 어노테이션을 이용, 특정 엔터티, 칼럼과의 관계를 명시하여 사용할 수 있다.
 * plant 엔터티의 id를 plant_id로 가져와서 참조하여 사용한다.
 */

data class PlantAndGardenPlantings(
    @Embedded
    val plant: Plant,

    @Relation(parentColumn = "plant_id", entityColumn = "garden_plant_id")
    val gardenPlantings: GardenPlanting

)
