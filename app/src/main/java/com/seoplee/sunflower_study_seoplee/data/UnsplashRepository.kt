package com.seoplee.sunflower_study_seoplee.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.reactivex.Flowable

interface UnsplashRepository {

    fun searchPictures(query: String): Flowable<PagingData<UnsplashPhoto>>

}