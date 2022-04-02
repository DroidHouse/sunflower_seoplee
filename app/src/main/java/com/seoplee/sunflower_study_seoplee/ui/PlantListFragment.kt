package com.seoplee.sunflower_study_seoplee.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.seoplee.sunflower_study_seoplee.adapters.PlantAdapter
import com.seoplee.sunflower_study_seoplee.adapters.PlantAdapterListener
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.databinding.FragmentPlantListBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class PlantListFragment : Fragment() {

    private lateinit var binding: FragmentPlantListBinding

    private val viewModel: PlantListViewModel by viewModels()

    private val adapter by lazy {
        PlantAdapter(
            adapterListener = object : PlantAdapterListener {
                override fun onClickItem(item: Plant) {
                    navigateToDetail(item)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlantListBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter

        return binding.root

    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPlantList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { adapter.submitList(it) },
                { Log.e(TAG, "load plantList Error ${it.message}") }
            )
    }

    private fun navigateToDetail(plant: Plant) {
        val direction = MainViewPagerFragmentDirections.actionMainViewPagerFragmentToPlantDetailFragment(plant.name)
        view?.findNavController()?.navigate(direction)
    }

    companion object {
        private const val TAG = "PlantListFragment"

        fun newInstance() = PlantListFragment()
    }

}