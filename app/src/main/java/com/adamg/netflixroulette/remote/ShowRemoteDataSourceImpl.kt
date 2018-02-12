package com.adamg.netflixroulette.remote

import com.adamg.netflixroulette.remote.mappers.ShowMapper
import com.adamg.netflixroulette.repository.model.RouletteFilter
import com.adamg.netflixroulette.repository.model.Show
import io.reactivex.Single

class ShowRemoteDataSourceImpl(private val netflixRouletteApi: NetflixRouletteApi,
                               private val showMapper: ShowMapper): ShowRemoteDataSource {

    override fun getRandomShow(filter: RouletteFilter): Single<Show> {
        return netflixRouletteApi.getRandomShow(filter.minimumScore, filter.showKind.type)
                .map { showMapper.toRepoShow(it) }
    }
}