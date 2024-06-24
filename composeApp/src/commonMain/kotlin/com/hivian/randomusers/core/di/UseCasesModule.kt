package com.hivian.randomusers.core.di

import com.hivian.randomusers.core.domain.usescases.LocalizationUseCase
import com.hivian.randomusers.core.domain.usescases.ShowAppMessageUseCase
import com.hivian.randomusers.core.domain.usescases.TranslateResourceUseCase
import org.koin.dsl.module

val coreUseCasesModule = module {
    factory { LocalizationUseCase(get()) }
    factory { ShowAppMessageUseCase(get()) }
    factory { TranslateResourceUseCase(get()) }
}
