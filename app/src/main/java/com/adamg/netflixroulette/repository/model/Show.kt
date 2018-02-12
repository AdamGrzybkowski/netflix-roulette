package com.adamg.netflixroulette.repository.model

import com.adamg.netflixroulette.remote.model.ContentType

data class Show(
        val id: String,
        val title: String,
        val overview: String,
        val runtime: Int,
        val contentType: ContentType,
        val posterUrl: String?,
        val imdbRating: String,
        val releaseYear: Int,
        val seasonCount: Int?,
        val netflixUrl: String?
)