package com.linkjf.climita.data.repository

import com.linkjf.climita.data.models.LocationEntity

interface LocationSearchRemote {

    suspend fun getLocations(query: String): List<LocationEntity>
}
