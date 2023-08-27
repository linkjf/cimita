package com.linkjf.climita.remote.repository

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.data.repository.LocationSearchRemote
import com.linkjf.climita.remote.api.LocationSearchService
import com.linkjf.climita.remote.mapper.LocationEntityMapper
import javax.inject.Inject

class LocationSearchRemoteImp @Inject constructor(
    private val locationSearchService: LocationSearchService,
    private val locationEntityMapper: LocationEntityMapper
) : LocationSearchRemote {
    override suspend fun getLocations(query: String): List<LocationEntity> {
        return locationSearchService.searchLocation(query).map { locationResponseItem ->
            locationEntityMapper.mapFromModel(locationResponseItem)
        }
    }


}
