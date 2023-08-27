package com.linkjf.climita.remote.mapper

interface EntityMapper<M, E> {
    fun mapFromModel(model: M): E
}
