package com.ebt.domain.usecase

interface SingleUseCase<Params, Type>: UseCase {
    suspend fun execute(params: Params): Type
}