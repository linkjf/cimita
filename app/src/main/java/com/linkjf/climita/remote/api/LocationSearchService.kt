package com.linkjf.climita.remote.api

import com.linkjf.climita.remote.models.LocationSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationSearchService {

    @GET(LOCATION_SEARCH_REQUEST)
    suspend fun searchLocation(
        @Query(QUERY_REQUEST_PARAM) query: String
    ): Response<LocationSearchResponse>

}
