package com.hivian.randomusers.di

import com.hivian.randomusers.core.di.coreUseCasesModule
import com.hivian.randomusers.core.di.dispatcherModule
import com.hivian.randomusers.core.di.servicesModule
import com.hivian.randomusers.homeFeature.di.repositoryModule
import com.hivian.randomusers.homeFeature.di.useCasesModule
import com.hivian.randomusers.homeFeature.di.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            dispatcherModule,
            com.hivian.randomusers.core.di.platformDatabaseModule,
            servicesModule,
            repositoryModule,
            coreUseCasesModule,
            useCasesModule,
            viewModelModule
        )
    }


//using in iOS
fun initKoin() = initKoin {}