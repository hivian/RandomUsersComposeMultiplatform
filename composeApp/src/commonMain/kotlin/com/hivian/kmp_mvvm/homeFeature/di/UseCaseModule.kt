package com.hivian.kmp_mvvm.homeFeature.di

import com.hivian.kmp_mvvm.homeFeature.domain.usecases.GetRandomUserByIdUseCase
import com.hivian.kmp_mvvm.homeFeature.domain.usecases.GetRandomUsersUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module {
    factory { GetRandomUsersUseCase(get()) }
    factory { GetRandomUserByIdUseCase(get()) }
}
