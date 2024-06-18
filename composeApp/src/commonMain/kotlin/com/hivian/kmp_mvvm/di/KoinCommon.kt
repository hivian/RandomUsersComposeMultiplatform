package com.hivian.kmp_mvvm.di

import com.hivian.kmp_mvvm.di.modules.networkModule
import com.hivian.kmp_mvvm.di.modules.platformDatabaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            platformDatabaseModule
        )
    }


//using in iOS
fun initKoin() = initKoin {}