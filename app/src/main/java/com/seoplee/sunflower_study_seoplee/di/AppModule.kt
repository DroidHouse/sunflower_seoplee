package com.seoplee.sunflower_study_seoplee.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.seoplee.sunflower_study_seoplee.BuildConfig
import com.seoplee.sunflower_study_seoplee.MyApplication
import com.seoplee.sunflower_study_seoplee.MyApplication.Companion.appContext
import com.seoplee.sunflower_study_seoplee.api.UnsplashService
import com.seoplee.sunflower_study_seoplee.api.Url
import com.seoplee.sunflower_study_seoplee.data.AppDataBase
import com.seoplee.sunflower_study_seoplee.data.GardenPlantingDao
import com.seoplee.sunflower_study_seoplee.data.PlantDao
import com.seoplee.sunflower_study_seoplee.utils.PLANT_DATA_FILENAME
import com.seoplee.sunflower_study_seoplee.workers.SeedDatabaseWorker
import com.seoplee.sunflower_study_seoplee.workers.SeedDatabaseWorker.Companion.KEY_FILENAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    fun provideUnsplashUrl() = Url.UNSPLASH_URL

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(Url.UNSPLASH_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConvertFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashService = retrofit.create(UnsplashService::class.java)

    @Provides
    @Singleton
    fun provideLocalDB() : AppDataBase = Room
        .databaseBuilder(appContext!!, AppDataBase::class.java, AppDataBase.DB_NAME)
        .addCallback(
            object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                        .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
                        .build()
                    WorkManager.getInstance(appContext!!).enqueue(request)
                }
            }
        )
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