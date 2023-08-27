package com.linkjf.climita.remote.mapper

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.remote.models.LocationSearchResponseItem

class LocationEntityMapper : EntityMapper<LocationSearchResponseItem, LocationEntity> {
    override fun mapFromModel(model: LocationSearchResponseItem): LocationEntity {
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
