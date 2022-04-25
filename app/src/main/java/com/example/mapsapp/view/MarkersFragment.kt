package com.example.mapsapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.mapsapp.adapter.RVMarkersAdapter
import com.example.mapsapp.databinding.FragmentMarkersBinding
import com.example.mapsapp.domain.Marker
import com.example.mapsapp.tool.Navigation
import com.example.mapsapp.viewmodel.SingleViewModel

class MarkersFragment : BaseFragment<FragmentMarkersBinding>(FragmentMarkersBinding::inflate) {
    private val viewModel: SingleViewModel by activityViewModels()
    private var adapter: RVMarkersAdapter? = null
    private val navigation by lazy { Navigation(parentFragmentManager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.markers.observe(viewLifecycleOwner) { setData(it) }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(list: List<Marker>) {
        binding.rvMarkers.adapter = adapter
        adapter?.list?.apply {
            clear()
            addAll(list)
        }
        adapter?.notifyDataSetChanged()
    }

    private fun initAdapter() {
        adapter = RVMarkersAdapter().apply {
            clickListener = { marker ->
                marker.id?.let {
                    navigation.toMarker(it)
                }
            }
        }
    }
}