package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.homeFeature.domain.usecases.GetRandomUserByIdUseCase
import com.hivian.kmp_mvvm.homeFeature.domain.usecases.GetRandomUsersUseCase
import com.hivian.kmp_mvvm.homeFeature.domain.usecases.LocalizationUseCase
import com.hivian.kmp_mvvm.homeFeature.domain.usecases.ShowAppMessageUseCase
import com.hivian.kmp_mvvm.homeFeature.domain.usecases.TranslateResourceUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module {
    factory { LocalizationUseCase(get()) }
    factory { GetRandomUsersUseCase(get()) }
    factory { GetRandomUserByIdUseCase(get()) }
    factory { ShowAppMessageUseCase(get()) }
    factory { TranslateResourceUseCase(get()) }
}
