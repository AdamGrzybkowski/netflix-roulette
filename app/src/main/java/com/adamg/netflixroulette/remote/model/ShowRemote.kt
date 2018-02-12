package com.adamg.netflixroulette.remote.model

import com.squareup.moshi.Json

data class ShowRemote(
        val id: String,
        val title: String,
        val overview: String,
        val runtime: Int,
        val availability: List<StreamSource>?,
        @Json(name = "content_type") val contentType: ContentType,
        @Json(name = "has_poster") val hasPoster: Boolean,
        @Json(name = "imdb_rating") val imdbRating: String,
        @Json(name = "released_on") val releasedOn: String,
        @Json(name = "season_count") val seasonCount: Int?
)