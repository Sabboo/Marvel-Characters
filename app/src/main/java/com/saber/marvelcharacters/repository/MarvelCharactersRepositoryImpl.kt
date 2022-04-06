package com.saber.marvelcharacters.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.saber.marvelcharacters.api.MarvelAPI
import com.saber.marvelcharacters.db.MarvelDb


class MarvelCharactersRepositoryImpl(val db: MarvelDb, val marvelAPI: MarvelAPI) :
    MarvelCharactersRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun marvelCharacters(pageSize: Int, offset: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = PageKeyedRemoteMediator(db, marvelAPI, offset)
    ) {
        db.characters().getMarvelCharacters()
    }.flow
}
