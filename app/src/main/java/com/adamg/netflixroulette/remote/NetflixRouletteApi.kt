package com.adamg.netflixroulette.remote

import com.adamg.netflixroulette.remote.model.ShowRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetflixRouletteApi {

    @GET("roulette/netflix?nocache=true&sources=netflix")
    fun getRandomShow(
            @Query("minimumScore") minimumScore: Int,
            @Query("kind") showKind: Int): Single<ShowRemote>
}