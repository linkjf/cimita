package com.linkjf.climita.data.repository

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.remote.common.Result


interface LocationSearchDataSource {

    suspend fun getLocations(query: String): Result<List<LocationEntity>>
}
