package com.adamg.gimmemovie.remote

import com.adamg.gimmemovie.repository.model.RouletteFilter
import com.adamg.gimmemovie.repository.model.Show
import io.reactivex.Single

interface ShowRemoteDataSource {
    fun getRandomShow(filter: RouletteFilter): Single<Show>
}