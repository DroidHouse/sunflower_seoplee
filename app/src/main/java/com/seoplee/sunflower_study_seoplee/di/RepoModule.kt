package com.seoplee.sunflower_study_seoplee.di

import com.seoplee.sunflower_study_seoplee.data.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindPlantRepository(
        plantRepositoryImpl: PlantRepositoryImpl
    ): PlantRepository

    @Binds
    abstract fun gardenPlantingRepository(
        gardenPlantingRepositoryImpl: GardenPlantingRepositoryImpl
    ): GardenPlantingRepository

    @Binds
    abstract fun unsplashRepository(
        unsplashRepositoryImpl: UnsplashRepositoryImpl
    ): UnsplashRepository
}