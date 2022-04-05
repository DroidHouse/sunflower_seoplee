package com.seoplee.sunflower_study_seoplee.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.seoplee.sunflower_study_seoplee.api.UnsplashService
import io.reactivex.Flowable
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val service : UnsplashService
): UnsplashRepository {
    override fun searchPictures(query: String): Flowable<PagingData<UnsplashPhoto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false),
            pagingSourceFactory = { UnsplashPagingSource(service, query) }
        ).flowable
    }
}