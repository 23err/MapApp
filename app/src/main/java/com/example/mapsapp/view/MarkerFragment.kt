package com.example.mapsapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mapsapp.R
import com.example.mapsapp.databinding.FragmentMarkerBinding
import com.example.mapsapp.domain.Marker
import com.example.mapsapp.tool.Navigation
import com.example.mapsapp.viewmodel.SingleViewModel

class MarkerFragment : BaseFragment<FragmentMarkerBinding>(FragmentMarkerBinding::inflate) {

    private val viewModel: SingleViewModel by activityViewModels()
    private val navigation by lazy { Navigation(parentFragmentManager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong(ID_MARKER)?.let {
            viewModel.getMarker(it).observe(viewLifecycleOwner) { marker -> setData(marker) }
        }
        setClickListeners()
    }

    private fun setClickListeners() = with(binding) {
        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val annotation = etAnnotation.text.toString().trim()
            if (title.isNotEmpty() && annotation.isNotEmpty()) {
                viewModel.updateMarker(
                    title,
                    annotation
                )
                navigation.popBackStack()
            } else {
                Toast.makeText(context, getString(R.string.input_string), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setData(marker: Marker?) = with(binding) {
        marker?.let {
            it.title?.let { title -> etTitle.setText(title) }
            it.annotation?.let { annotation -> etAnnotation.setText(annotation) }
            tvLat.setText(it.lat.toString())
            tvLong.setText(it.lng.toString())
        }
    }

    companion object {
        fun getInstance(id: Long): Fragment = MarkerFragment().apply {
            arguments = bundleOf(ID_MARKER to id)
        }

        private const val ID_MARKER = "id_marker"
    }
}