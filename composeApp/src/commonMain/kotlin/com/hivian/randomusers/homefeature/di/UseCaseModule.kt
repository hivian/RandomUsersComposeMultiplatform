package com.hivian.randomusers.homefeature.di

import com.hivian.randomusers.homefeature.domain.usecases.GetRandomUserByIdUseCase
import com.hivian.randomusers.homefeature.domain.usecases.GetRandomUsersUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module {
    factory { GetRandomUsersUseCase(get()) }
    factory { GetRandomUserByIdUseCase(get()) }
}
