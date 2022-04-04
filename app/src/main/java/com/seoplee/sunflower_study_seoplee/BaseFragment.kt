package com.seoplee.sunflower_study_seoplee

import androidx.fragment.app.Fragment

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    abstract val viewModel : VM

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearCompositeDisposable()
    }
}