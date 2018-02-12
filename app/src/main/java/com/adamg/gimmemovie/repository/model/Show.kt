package com.adamg.gimmemovie.repository.model

import com.adamg.gimmemovie.remote.model.ContentType

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