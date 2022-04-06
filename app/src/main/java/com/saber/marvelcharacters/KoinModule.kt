package com.saber.marvelcharacters

import android.app.Application
import com.saber.marvelcharacters.api.MarvelAPI
import com.saber.marvelcharacters.db.MarvelDb
import com.saber.marvelcharacters.repository.MarvelCharactersRepositoryImpl
import com.saber.marvelcharacters.ui.MarvelCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { provideApi() }
    single { provideDB(get()) }
    single { provideRepository(get(), get()) }
    viewModel { provideViewModel(get()) }
}

fun provideApi() = MarvelAPI.create()

fun provideDB(app: Application) = MarvelDb.create(app)

fun provideRepository(db: MarvelDb, api: MarvelAPI) = MarvelCharactersRepositoryImpl(
    db = db,
    marvelAPI = api
)

fun provideViewModel(repository: MarvelCharactersRepositoryImpl) = MarvelCharactersViewModel(repository)