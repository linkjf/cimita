package com.linkjf.climita.data.mapper

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.domain.models.Location
import javax.inject.Inject

class LocationMapper @Inject constructor() : Mapper<LocationEntity, Location> {

    override fun mapFromEntity(entity: LocationEntity): Location {
        return Location(
            country = entity.country,
            id = entity.id,
            lat = entity.lat,
            lon = entity.lon,
            name = entity.name,
            region = entity.region,
            url = entity.url
        )
    }

    override fun mapToEntity(model: Location): LocationEntity {
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
