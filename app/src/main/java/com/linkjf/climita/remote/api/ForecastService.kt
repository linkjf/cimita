package com.linkjf.climita.remote.api

import com.linkjf.climita.remote.models.ForecastResponse
import com.linkjf.climita.remote.models.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("search.json")
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("days") days: Int
    ): Response<ForecastResponse>

}
