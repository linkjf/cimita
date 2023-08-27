package com.linkjf.climita.data.repository

import com.linkjf.climita.data.mapper.LocationMapper
import com.linkjf.climita.data.source.LocationSearchRemoteDataSource
import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.domain.repository.LocationSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationSearchRepositoryImp @Inject constructor(
    private val locationSearchRemoteDataSource: LocationSearchRemoteDataSource,
    private val mapper: LocationMapper
) : LocationSearchRepository {
    override suspend fun getLocations(query: String): Flow<List<Location>> = flow {
        val locationList =
            locationSearchRemoteDataSource.getLocations(query).map { locationEntity ->
                mapper.mapFromEntity(locationEntity)
            }
        emit(locationList)
    }


}
