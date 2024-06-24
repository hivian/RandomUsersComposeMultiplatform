package com.hivian.kmp_mvvm.core.di

import com.hivian.kmp_mvvm.core.datasources.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<DatabaseDriverFactory> { DatabaseDriverFactory(get()) }
}
