package com.seoplee.sunflower_study_seoplee.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.seoplee.sunflower_study_seoplee.R
import com.seoplee.sunflower_study_seoplee.adapters.MY_GARDEN_PAGE_INDEX
import com.seoplee.sunflower_study_seoplee.adapters.MainViewPagerAdapter
import com.seoplee.sunflower_study_seoplee.adapters.PLANT_LIST_PAGE_INDEX
import com.seoplee.sunflower_study_seoplee.databinding.FragmentMainViewpagerBinding

class MainViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentMainViewpagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = MainViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> "garden"
            PLANT_LIST_PAGE_INDEX -> "plantList"
            else -> null
        }
    }

    companion object {
        fun newInstance() = MainViewPagerFragment()
    }
}