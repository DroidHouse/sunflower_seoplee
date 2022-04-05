package com.seoplee.sunflower_study_seoplee.api

import com.seoplee.sunflower_study_seoplee.BuildConfig
import com.seoplee.sunflower_study_seoplee.data.UnsplashSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {

    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ): Single<UnsplashSearchResponse>

}
