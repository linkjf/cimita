package com.linkjf.climita.remote.repository

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.data.repository.LocationSearchRemote
import com.linkjf.climita.remote.api.LocationSearchService
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Exception
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.common.safeRequest
import com.linkjf.climita.remote.mapper.LocationEntityMapper
import javax.inject.Inject

class LocationSearchRemoteImp @Inject constructor(
    private val locationSearchService: LocationSearchService,
    private val locationEntityMapper: LocationEntityMapper
) : LocationSearchRemote {
    override suspend fun getLocations(query: String): Result<List<LocationEntity>> {
        val result = safeRequest {
            locationSearchService.searchLocation(query)
        }

        return when (result) {
            is Success -> {
                val modelList = result.data.map {
                    locationEntityMapper.mapFromModel(it)
                }
                Success(modelList)
            }

            is Error -> Error(code = result.code, message = result.message)
            is Exception -> Exception(e = result.e)
        }
    }
}
