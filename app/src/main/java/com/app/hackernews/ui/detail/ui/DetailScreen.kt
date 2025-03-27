package com.app.hackernews.ui.detail.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.hackernews.R
import com.app.hackernews.databinding.DetailScreenBinding
import com.app.hackernews.ui.detail.viewModel.DetailScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.detail_screen) {
    private var _binding: DetailScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var customTabLauncher: ActivityResultLauncher<Intent>
    private val viewModel: DetailScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customTabLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                Toast.makeText(requireContext(), "Chrome Custom Tabs cerrado", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailScreenBinding.bind(view)
        setUpViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViews() {
        viewModel.openCustomTab("https://www.google.com", customTabLauncher)
    }
}
