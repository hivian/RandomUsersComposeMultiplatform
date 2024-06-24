package com.hivian.randomusers.homefeature.di

import com.hivian.randomusers.homefeature.data.services.RandomUsersService
import com.hivian.randomusers.homefeature.domain.services.IRandomUsersService
import org.koin.dsl.module

val repositoryModule = module {
    single<IRandomUsersService> { RandomUsersService(get(), get()) }
}