package com.adamg.gimmemovie.dagger.modules

import com.adamg.gimmemovie.repository.RouletteRepository
import com.adamg.gimmemovie.view.roulette.RouletteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
open class RouletteActivityModule {

    @Provides
    fun provideSongsViewModelFactory(rouletteRepository: RouletteRepository): RouletteViewModelFactory {
        return RouletteViewModelFactory(rouletteRepository)
    }
}