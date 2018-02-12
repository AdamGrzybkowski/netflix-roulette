package com.adamg.netflixroulette.repository

import com.adamg.netflixroulette.remote.ShowRemoteDataSource
import com.adamg.netflixroulette.repository.model.RouletteFilter
import com.adamg.netflixroulette.repository.model.Show
import io.reactivex.Single
import javax.inject.Inject

class ShowRepositoryImpl @Inject constructor(private val showRemoteDataSource: ShowRemoteDataSource) : ShowRepository {

    override fun getRandomShow(rouletteFilter: RouletteFilter): Single<Show> {
        return showRemoteDataSource.getRandomShow(rouletteFilter)
    }

}