package com.adamg.netflixroulette.dagger.modules

import com.adamg.netflixroulette.repository.ShowRepository
import com.adamg.netflixroulette.view.roulette.RouletteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
open class RouletteActivityModule {

    @Provides
    fun provideSongsViewModelFactory(showRepository: ShowRepository): RouletteViewModelFactory {
        return RouletteViewModelFactory(showRepository)
    }
}