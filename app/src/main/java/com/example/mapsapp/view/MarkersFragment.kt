package com.example.mapsapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.mapsapp.adapter.RVMarkersAdapter
import com.example.mapsapp.databinding.FragmentMarkersBinding
import com.example.mapsapp.domain.Marker
import com.example.mapsapp.viewmodel.MapViewModel

class MarkersFragment : BaseFragment<FragmentMarkersBinding>(FragmentMarkersBinding::inflate) {
    private val viewModel: MapViewModel by activityViewModels()
    private var adapter: RVMarkersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.markers.observe(viewLifecycleOwner) { setData(it) }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(list: List<Marker>) {
        adapter?.list?.apply {
            clear()
            addAll(list)
        }
        adapter?.notifyDataSetChanged()
    }

    private fun initAdapter() {
        adapter = RVMarkersAdapter().apply {
            clickListener = {
                Toast.makeText(context, "${it.lat}:${it.lng}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}