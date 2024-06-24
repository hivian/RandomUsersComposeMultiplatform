package com.hivian.randomusers.homeFeature.di

import com.hivian.randomusers.homeFeature.data.repository.RandomUsersRepository
import com.hivian.randomusers.homeFeature.domain.repository.IRandomUsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IRandomUsersRepository> { RandomUsersRepository(get(), get()) }
}