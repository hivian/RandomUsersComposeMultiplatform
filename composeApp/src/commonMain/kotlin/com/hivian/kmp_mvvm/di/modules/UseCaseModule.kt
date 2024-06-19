package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.basicFeature.domain.usecases.GetRandomUsersUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.LocalizationUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.ShowAppMessageUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.TranslateResourceUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation.NavigateBackUseCase
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation.NavigateToRandomUserDetailUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module {
    factory { LocalizationUseCase(get()) }
    factory { GetRandomUsersUseCase(get()) }
    factory { ShowAppMessageUseCase(get()) }
    factory { TranslateResourceUseCase(get()) }
    factory { NavigateToRandomUserDetailUseCase(get()) }
    factory { NavigateBackUseCase(get()) }
}
