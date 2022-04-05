package com.seoplee.sunflower_study_seoplee.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.seoplee.sunflower_study_seoplee.BaseFragment
import com.seoplee.sunflower_study_seoplee.adapters.GalleryAdapter
import com.seoplee.sunflower_study_seoplee.adapters.GalleryAdapterListener
import com.seoplee.sunflower_study_seoplee.adapters.GardenPlantingAdapter
import com.seoplee.sunflower_study_seoplee.adapters.GardenPlantingAdapterListener
import com.seoplee.sunflower_study_seoplee.data.Plant
import com.seoplee.sunflower_study_seoplee.data.PlantAndGardenPlantings
import com.seoplee.sunflower_study_seoplee.data.UnsplashPhoto
import com.seoplee.sunflower_study_seoplee.databinding.FragmentGalleryBinding
import com.seoplee.sunflower_study_seoplee.databinding.FragmentGardenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GalleryFragment : BaseFragment<GalleryViewModel>() {

    private lateinit var binding: FragmentGalleryBinding
    override val viewModel: GalleryViewModel by viewModels()
    private val adapter by lazy {
        GalleryAdapter(
            adapterListener = object : GalleryAdapterListener {
                override fun onClickItem(item: UnsplashPhoto) {
                    val uri = Uri.parse(item.user.attributionUrl)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewModel.photoList.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    companion object {
        private const val TAG = "GalleryFragment"

        fun newInstance() = GalleryFragment()
    }

}