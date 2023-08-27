package com.linkjf.climita.domain.repository

import com.linkjf.climita.domain.models.Location
import kotlinx.coroutines.flow.Flow

interface LocationSearchRepository {

    suspend fun getLocations(query: String): Flow<List<Location>>
}

