package com.adamg.gimmemovie.repository

import com.adamg.gimmemovie.repository.model.RouletteFilter
import com.adamg.gimmemovie.repository.model.Show
import io.reactivex.Single

interface ShowRepository {
    fun getRandomShow(rouletteFilter: RouletteFilter): Single<Show>
}