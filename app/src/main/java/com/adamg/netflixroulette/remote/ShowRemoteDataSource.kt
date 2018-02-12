package com.adamg.netflixroulette.remote

import com.adamg.netflixroulette.repository.model.RouletteFilter
import com.adamg.netflixroulette.repository.model.Show
import io.reactivex.Single

interface ShowRemoteDataSource {
    fun getRandomShow(filter: RouletteFilter): Single<Show>
}