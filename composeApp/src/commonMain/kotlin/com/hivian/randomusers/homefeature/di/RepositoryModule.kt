package com.hivian.randomusers.homefeature.di

import com.hivian.randomusers.homefeature.data.repository.RandomUsersRepository
import com.hivian.randomusers.homefeature.domain.repository.IRandomUsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IRandomUsersRepository> { RandomUsersRepository(get(), get()) }
}