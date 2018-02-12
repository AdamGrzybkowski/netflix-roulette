package com.adamg.gimmemovie.repository

import com.adamg.gimmemovie.remote.ShowRemoteDataSource
import com.adamg.gimmemovie.repository.model.RouletteFilter
import com.adamg.gimmemovie.repository.model.Show
import io.reactivex.Single
import javax.inject.Inject

class ShowRepositoryImpl @Inject constructor(private val showRemoteDataSource: ShowRemoteDataSource) : ShowRepository {

    override fun getRandomShow(rouletteFilter: RouletteFilter): Single<Show> {
        return showRemoteDataSource.getRandomShow(rouletteFilter)
    }

}