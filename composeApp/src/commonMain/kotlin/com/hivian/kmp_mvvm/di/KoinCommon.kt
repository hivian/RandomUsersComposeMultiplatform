package com.hivian.kmp_mvvm.di

import com.hivian.kmp_mvvm.di.modules.dispatcherModule
import com.hivian.kmp_mvvm.di.modules.servicesModule
import com.hivian.kmp_mvvm.di.modules.platformDatabaseModule
import com.hivian.kmp_mvvm.di.modules.repositoryModule
import com.hivian.kmp_mvvm.di.modules.useCasesModule
import com.hivian.kmp_mvvm.di.modules.viewModelModule
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
            useCasesModule,
            viewModelModule
        )
    }


//using in iOS
fun initKoin() = initKoin {}