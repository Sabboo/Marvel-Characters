package com.saber.marvelcharacters.repository

import androidx.paging.PagingData
import com.saber.marvelcharacters.data.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelCharactersRepository {
    fun marvelCharacters(
        pageSize: Int,
        offset: Int
    ): Flow<PagingData<MarvelCharacter>>
}