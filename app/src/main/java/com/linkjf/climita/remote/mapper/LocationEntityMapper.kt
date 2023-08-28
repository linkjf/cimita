package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.remote.models.LocationResponse
import javax.inject.Inject

class LocationEntityMapper @Inject constructor() :
    EntityMapper<LocationResponse, LocationEntity> {
    override fun mapFromModel(model: LocationResponse): LocationEntity {
        return LocationEntity(
            country = model.country,
            id = model.id,
            lat = model.lat,
            lon = model.lon,
            name = model.name,
            region = model.region,
            url = model.url
        )
    }
}
