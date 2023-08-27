package com.linkjf.climita.remote.api

import com.linkjf.climita.remote.models.LocationSearchResponse
import retrofit2.http.GET

interface LocationSearchService {

    @GET("search.json")
    suspend fun searchLocation(location: String): LocationSearchResponse

}
