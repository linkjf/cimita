package com.linkjf.climita.data.repository

import com.linkjf.climita.data.mapper.LocationMapper
import com.linkjf.climita.data.source.LocationSearchRemoteDataSource
import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.domain.repository.LocationSearchRepository
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationSearchRepositoryImp @Inject constructor(
    private val locationSearchRemoteDataSource: LocationSearchRemoteDataSource,
    private val mapper: LocationMapper
) : LocationSearchRepository {
    override suspend fun getLocations(query: String): Flow<Result<List<Location>>> = flow {
        val result =
            locationSearchRemoteDataSource.getLocations(query)

        if (result is Success) {
            val locationList = result.data.map { locationEntity ->
                mapper.mapFromEntity(locationEntity)
            }
            emit(Success(locationList))
        } else
            emit(Error(-1, null))
    }


}
