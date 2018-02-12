package com.adamg.gimmemovie.dagger.modules

import com.adamg.gimmemovie.repository.ShowRepository
import com.adamg.gimmemovie.view.roulette.RouletteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
open class RouletteActivityModule {

    @Provides
    fun provideSongsViewModelFactory(showRepository: ShowRepository): RouletteViewModelFactory {
        return RouletteViewModelFactory(showRepository)
    }
}