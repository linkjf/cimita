package com.linkjf.climita.data.source

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.data.repository.LocationSearchDataSource
import com.linkjf.climita.data.repository.LocationSearchRemote
import com.linkjf.climita.remote.common.Result
import javax.inject.Inject

class LocationSearchRemoteDataSource @Inject constructor(
    private val locationRemote: LocationSearchRemote
) : LocationSearchDataSource {
    override suspend fun getLocations(query: String): Result<List<LocationEntity>> {
        return locationRemote.getLocations(query)
    }


}
