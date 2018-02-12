package com.adamg.netflixroulette.repository

import com.adamg.netflixroulette.repository.model.RouletteFilter
import com.adamg.netflixroulette.repository.model.Show
import io.reactivex.Single

interface ShowRepository {
    fun getRandomShow(rouletteFilter: RouletteFilter): Single<Show>
}