package com.seoplee.sunflower_study_seoplee.di

import androidx.room.Room
import com.seoplee.sunflower_study_seoplee.MyApplication
import com.seoplee.sunflower_study_seoplee.api.UnsplashService
import com.seoplee.sunflower_study_seoplee.api.Url
import com.seoplee.sunflower_study_seoplee.data.AppDataBase
import com.seoplee.sunflower_study_seoplee.data.GardenPlantingDao
import com.seoplee.sunflower_study_seoplee.data.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    fun provideUnsplashUrl() = Url.UNSPLASH_URL

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashService = retrofit.create(UnsplashService::class.java)

    @Provides
    @Singleton
    fun provideLocalDB() : AppDataBase = Room
        .databaseBuilder(MyApplication.appContext!!, AppDataBase::class.java, AppDataBase.DB_NAME)
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideGardenPlantingDao(appDataBase: AppDataBase): GardenPlantingDao = appDataBase.gardenPlantingDao()

    @Provides
    @Singleton
    fun providePlantDao(appDataBase: AppDataBase): PlantDao = appDataBase.plantDao()

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}