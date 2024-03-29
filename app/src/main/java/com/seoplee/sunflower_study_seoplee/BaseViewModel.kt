package com.seoplee.sunflower_study_seoplee

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    fun clearCompositeDisposable() = compositeDisposable.clear()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}