package com.linkjf.climita.remote.repository

import com.linkjf.climita.BuildConfig
import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.data.repository.LocationSearchRemote
import com.linkjf.climita.remote.api.LocationSearchService
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.common.safeRequest
import com.linkjf.climita.remote.mapper.LocationEntityMapper
import javax.inject.Inject

class LocationSearchRemoteImp @Inject constructor(
    private val locationSearchService: LocationSearchService,
    private val locationEntityMapper: LocationEntityMapper
) : LocationSearchRemote {
    override suspend fun getLocations(query: String): Result<List<LocationEntity>> {
        val apiKey = BuildConfig.API_KEY
        val result = safeRequest {
            locationSearchService.searchLocation(apiKey, query)
        }

        return if (result is Success) {
            val modelList = result.data.map {
                locationEntityMapper.mapFromModel(it)
            }
            Success(modelList)
        } else
            Result.Error(-1, null)
    }


}
