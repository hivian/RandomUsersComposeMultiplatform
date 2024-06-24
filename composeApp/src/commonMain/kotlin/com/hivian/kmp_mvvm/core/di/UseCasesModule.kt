package com.hivian.kmp_mvvm.core.di

import com.hivian.kmp_mvvm.homeFeature.domain.usecases.LocalizationUseCase
import com.hivian.kmp_mvvm.homeFeature.domain.usecases.ShowAppMessageUseCase
import com.hivian.kmp_mvvm.homeFeature.domain.usecases.TranslateResourceUseCase
import org.koin.dsl.module

val coreUseCasesModule = module {
    factory { LocalizationUseCase(get()) }
    factory { ShowAppMessageUseCase(get()) }
    factory { TranslateResourceUseCase(get()) }
}
