package com.seoplee.sunflower_study_seoplee.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.seoplee.sunflower_study_seoplee.BaseViewModel
import com.seoplee.sunflower_study_seoplee.data.UnsplashPhoto
import com.seoplee.sunflower_study_seoplee.data.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val unsplashRepository: UnsplashRepository
) : BaseViewModel() {

    private val _photoList = MutableLiveData<PagingData<UnsplashPhoto>>()
    val photoList : LiveData<PagingData<UnsplashPhoto>>
        get() = _photoList

    private val query: String= savedStateHandle.get<String>(PLANT_NAME_SAVED_STATE_KEY)!!

    init {
        searchPictures()
    }

    private fun searchPictures() {
        compositeDisposable.add(
            unsplashRepository.searchPictures(query).cachedIn(viewModelScope).subscribe{
                _photoList.postValue(it)
            }
        )
    }

    companion object {
        const val TAG = "GalleryViewModel"

        private const val PLANT_NAME_SAVED_STATE_KEY = "plantName"
    }
}