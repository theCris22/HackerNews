package com.app.hackernews.ui.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.hackernews.R
import com.app.hackernews.data.network.models.Hit
import com.app.hackernews.data.network.models.RequestState
import com.app.hackernews.databinding.HomeScreenBinding
import com.app.hackernews.ui.home.adapters.HackerNewsAdapter
import com.app.hackernews.ui.home.viewModel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.home_screen) {
    private var _binding: HomeScreenBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { HackerNewsAdapter(requireContext(), onItemClick = { itemClick(it) }) }
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeScreenBinding.bind(view)
        setUpViews()
        setUpListeners()
        setUpObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViews() =
        with(binding.recyclerNews) {
            setHasFixedSize(true)
            adapter = this@HomeScreen.adapter
        }

    private fun setUpListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAndroidNews()
        }
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.androidNewsState.collect { state ->
                    when (state) {
                        is RequestState.Success -> handleSuccess(state.data.hits)
                        is RequestState.Error, RequestState.Idle -> handleState(false)
                        RequestState.Loading -> handleState(true)
                    }
                }
            }
        }
    }

    private fun handleSuccess(data: List<Hit>) {
        handleState(false)
        adapter.updateData(data)
    }

    private fun handleState(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun itemClick(hit: Hit) {
        Toast.makeText(requireContext(), hit.toString(), Toast.LENGTH_LONG).show()
    }
}
