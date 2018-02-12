package com.adamg.gimmemovie.repository.model

data class RouletteFilter(
        val minimumScore: Int = 0,
        val showKind: ShowKind = ShowKind.ANY_SHOW
)