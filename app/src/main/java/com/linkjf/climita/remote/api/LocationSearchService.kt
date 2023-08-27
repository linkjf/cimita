package com.linkjf.climita.remote.api

import com.linkjf.climita.remote.models.LocationSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationSearchService {

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") key: String,
        @Query("q") query: String
    ): LocationSearchResponse

}
