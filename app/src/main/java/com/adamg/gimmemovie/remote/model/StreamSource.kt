package com.adamg.gimmemovie.remote.model

import com.squareup.moshi.Json

data class StreamSource(
    @Json(name = "source_name") val sourceName: String,
    @Json(name = "source_data") val sourceData: SourceData
)