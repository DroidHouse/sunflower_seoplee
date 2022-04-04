package com.seoplee.sunflower_study_seoplee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.seoplee.sunflower_study_seoplee.databinding.FragmentPlantDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlantDetailBinding

    private val viewModel: PlantDetailViewModel by viewModels()

    private val args: PlantDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = args.plantId
        viewModel.plantId = args.plantId

    }

    companion object {
        private const val TAG = "PlantDetailFragment"

        fun newInstance() = PlantDetailFragment()
    }

}