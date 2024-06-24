package com.hivian.randomusers.homeFeature.di

import com.hivian.randomusers.homeFeature.domain.usecases.GetRandomUserByIdUseCase
import com.hivian.randomusers.homeFeature.domain.usecases.GetRandomUsersUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module {
    factory { GetRandomUsersUseCase(get()) }
    factory { GetRandomUserByIdUseCase(get()) }
}
