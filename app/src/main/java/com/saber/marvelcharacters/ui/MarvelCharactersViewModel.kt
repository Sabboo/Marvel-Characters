package com.saber.marvelcharacters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.saber.marvelcharacters.repository.MarvelCharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MarvelCharactersViewModel(repository: MarvelCharactersRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val marvelCharacters = repository.marvelCharacters(20, 0)
        .cachedIn(viewModelScope)

}