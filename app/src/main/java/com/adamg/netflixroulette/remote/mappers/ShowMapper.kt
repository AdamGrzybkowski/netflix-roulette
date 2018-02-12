package com.adamg.netflixroulette.remote.mappers

import com.adamg.netflixroulette.remote.model.ContentType
import com.adamg.netflixroulette.remote.model.ShowRemote
import com.adamg.netflixroulette.repository.model.Show
import com.adamg.netflixroulette.util.getYear
import com.adamg.netflixroulette.util.parseDate
import javax.inject.Inject

class ShowMapper @Inject constructor() {

    fun toRepoShow(showRemote: ShowRemote): Show {
        return Show(
                id = showRemote.id,
                title = showRemote.title,
                contentType = showRemote.contentType,
                imdbRating = showRemote.imdbRating,
                overview = showRemote.overview,
                runtime = showRemote.runtime,
                posterUrl = getPosterUrl(showRemote),
                seasonCount = showRemote.seasonCount,
                netflixUrl = getNetflixUrl(showRemote),
                releaseYear = parseDate(showRemote.releasedOn).getYear()
        )
    }

    private fun getNetflixUrl(showRemote: ShowRemote): String? {
        val netflixSource = showRemote.availability.orEmpty()
                .firstOrNull { streamSource -> streamSource.sourceName == "netflix" }

        return netflixSource?.sourceData?.links?.android
    }

    private fun getPosterUrl(showRemote: ShowRemote): String? {
        return if (showRemote.hasPoster) {
            when (showRemote.contentType) {
                ContentType.TV_SHOW -> String.format(TV_SHOW_POSTER_IRL, showRemote.id)
                ContentType.MOVIE -> String.format(MOVIE_POSTER_IRL, showRemote.id)
            }
        } else {
            null
        }
    }

    companion object {
        const val MOVIE_POSTER_IRL = "https://img.reelgood.com/content/movie/%s/poster-780.jpg"
        const val TV_SHOW_POSTER_IRL = "https://img.reelgood.com/content/show/%s/poster-780.jpg"
    }

}