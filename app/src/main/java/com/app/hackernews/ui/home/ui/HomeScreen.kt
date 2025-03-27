package com.app.hackernews.ui.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.hackernews.R
import com.app.hackernews.databinding.HomeScreenBinding
import com.app.hackernews.ui.home.viewModel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.home_screen) {
    private lateinit var binding: HomeScreenBinding
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUpBinding()
    }

    private fun setUpBinding() {
        binding = HomeScreenBinding.bind(requireView())
    }
}
