package com.saber.marvelcharacters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.paging.LoadState
import com.saber.marvelcharacters.databinding.ActivityMainBinding
import com.saber.marvelcharacters.ui.adapter.CharactersAdapter
import com.saber.marvelcharacters.ui.adapter.CharactersLoadStateAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MarvelCharactersViewModel by viewModel()

    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initSwipeToRefresh()
    }

    private fun initAdapter() {
        adapter = CharactersAdapter()
        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CharactersLoadStateAdapter(adapter),
            footer = CharactersLoadStateAdapter(adapter)
        )

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadStates ->
                binding.swipeRefresh.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.marvelCharacters.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.list.scrollToPosition(0) }
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
    }

}
