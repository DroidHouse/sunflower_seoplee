package com.seoplee.sunflower_study_seoplee.data

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.seoplee.sunflower_study_seoplee.api.UnsplashService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Reference: https://medium.com/@fandygotama/paging-3-using-rxjava-3ddc218e4dba
 * */

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val service: UnsplashService,
    private val query: String
) : RxPagingSource<Int, UnsplashPhoto>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UnsplashPhoto>> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return service.searchPhotos(query, page, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }

    }

    private fun toLoadResult(data: UnsplashSearchResponse, page: Int) : LoadResult<Int, UnsplashPhoto> {
        return try {
            LoadResult.Page(
                data = data.results,
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == data.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
