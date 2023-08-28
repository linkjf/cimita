package com.linkjf.climita.domain.interactor

import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.domain.repository.LocationSearchRepository
import com.linkjf.climita.remote.common.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


typealias LocationSearchBaseUseCase = BaseUseCase<String, Flow<Result<List<Location>>>>

class LocationSearchUseCase @Inject constructor(
    private val locationRepository: LocationSearchRepository
) : LocationSearchBaseUseCase {
    override suspend operator fun invoke(parameter: String) =
        locationRepository.getLocations(parameter)

}
