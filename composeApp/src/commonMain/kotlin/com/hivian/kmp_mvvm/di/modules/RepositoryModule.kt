package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.basicFeature.data.repository.RandomUsersRepository
import com.hivian.kmp_mvvm.basicFeature.domain.repository.IRandomUsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IRandomUsersRepository> { RandomUsersRepository(get(), get()) }
}