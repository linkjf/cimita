package com.linkjf.climita.remote.api

import com.linkjf.climita.remote.models.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET(FORECAST_REQUEST)
    suspend fun getForecast(
        @Query(QUERY_REQUEST_PARAM) query: String,
        @Query(DAYS_REQUEST_PARAM) days: Int
    ): Response<ForecastResponse>

}
