package com.adamg.gimmemovie.remote

import com.adamg.gimmemovie.remote.mappers.ShowMapper
import com.adamg.gimmemovie.repository.model.RouletteFilter
import com.adamg.gimmemovie.repository.model.Show
import io.reactivex.Single

class ShowRemoteDataSourceImpl(private val netflixRouletteApi: NetflixRouletteApi,
                               private val showMapper: ShowMapper): ShowRemoteDataSource {

    override fun getRandomShow(filter: RouletteFilter): Single<Show> {
        return netflixRouletteApi.getRandomShow(filter.minimumScore, filter.showKind.type)
                .map { showMapper.toRepoShow(it) }
    }
}