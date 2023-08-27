package com.linkjf.climita.domain.interactor

interface BaseUseCase<in parameter, out Result> {

    suspend operator fun invoke(parameter: parameter): Result
}
