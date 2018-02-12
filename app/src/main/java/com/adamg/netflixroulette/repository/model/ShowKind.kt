package com.adamg.netflixroulette.repository.model

enum class ShowKind(val type: Int) {
    MOVIE(2),
    TV_SHOW(1),
    ANY_SHOW(0)
}