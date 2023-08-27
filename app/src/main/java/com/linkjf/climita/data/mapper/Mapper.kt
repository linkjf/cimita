package com.linkjf.climita.data.mapper

interface Mapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapToEntity(model: D): E
}
