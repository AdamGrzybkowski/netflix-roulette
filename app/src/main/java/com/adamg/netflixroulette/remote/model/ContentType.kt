package com.adamg.netflixroulette.remote.model

import com.squareup.moshi.Json

enum class ContentType {
    @Json(name = "s")
    TV_SHOW,
    @Json(name = "m")
    MOVIE
}