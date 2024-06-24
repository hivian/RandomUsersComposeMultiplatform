package com.hivian.randomusers.core.di

import com.hivian.randomusers.homeFeature.domain.usecases.LocalizationUseCase
import com.hivian.randomusers.homeFeature.domain.usecases.ShowAppMessageUseCase
import com.hivian.randomusers.homeFeature.domain.usecases.TranslateResourceUseCase
import org.koin.dsl.module

val coreUseCasesModule = module {
    factory { LocalizationUseCase(get()) }
    factory { ShowAppMessageUseCase(get()) }
    factory { TranslateResourceUseCase(get()) }
}
