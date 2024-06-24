package com.hivian.kmp_mvvm.homeFeature.di

import com.hivian.kmp_mvvm.homeFeature.data.repository.RandomUsersRepository
import com.hivian.kmp_mvvm.homeFeature.domain.repository.IRandomUsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IRandomUsersRepository> { RandomUsersRepository(get(), get()) }
}