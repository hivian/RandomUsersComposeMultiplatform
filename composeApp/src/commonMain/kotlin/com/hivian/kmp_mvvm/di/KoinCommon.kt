package com.hivian.kmp_mvvm.di

import com.hivian.kmp_mvvm.core.di.coreUseCasesModule
import com.hivian.kmp_mvvm.core.di.dispatcherModule
import com.hivian.kmp_mvvm.core.di.servicesModule
import com.hivian.kmp_mvvm.di.modules.platformDatabaseModule
import com.hivian.kmp_mvvm.homeFeature.di.repositoryModule
import com.hivian.kmp_mvvm.homeFeature.di.useCasesModule
import com.hivian.kmp_mvvm.homeFeature.di.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            dispatcherModule,
            platformDatabaseModule,
            servicesModule,
            repositoryModule,
            coreUseCasesModule,
            useCasesModule,
            viewModelModule
        )
    }


//using in iOS
fun initKoin() = initKoin {}