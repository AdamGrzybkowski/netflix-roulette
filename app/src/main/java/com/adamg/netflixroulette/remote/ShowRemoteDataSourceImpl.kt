package com.adamg.netflixroulette.remote

import com.adamg.netflixroulette.remote.mappers.ShowMapper
import com.adamg.netflixroulette.remote.model.ContentType
import com.adamg.netflixroulette.remote.model.ShowRemote
import com.adamg.netflixroulette.repository.model.RouletteFilter
import com.adamg.netflixroulette.repository.model.Show
import io.reactivex.Single

class ShowRemoteDataSourceImpl(private val netflixRouletteApi: NetflixRouletteApi,
                               private val showMapper: ShowMapper): ShowRemoteDataSource {

    override fun getRandomShow(filter: RouletteFilter): Single<Show> {
        return netflixRouletteApi.getRandomShow(filter.minimumScore, filter.showKind.type)
                .flatMap { getFullShow(it) }
                .map { showMapper.toRepoShow(it) }
    }

    private fun getFullShow(show: ShowRemote): Single<ShowRemote> {
        return when (show.contentType) {
            ContentType.MOVIE -> getFullMovie(show)
            ContentType.TV_SHOW -> getFullTvShow(show)
        }
    }

    private fun getFullMovie(show: ShowRemote): Single<ShowRemote> {
        return netflixRouletteApi.getMoview(show.id)
                .map { fullShow ->
                    show.copy(
                            availability = fullShow.availability,
                            trailer = fullShow.trailer
                    )
                }
    }

    private fun getFullTvShow(show: ShowRemote): Single<ShowRemote> {
        return netflixRouletteApi.getTvShow(show.id)
                .map { fullShow ->
                    show.copy(
                            trailer = fullShow.trailer
                    )
                }
    }

}