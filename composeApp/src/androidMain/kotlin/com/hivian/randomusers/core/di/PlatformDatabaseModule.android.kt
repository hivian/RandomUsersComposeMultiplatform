package com.hivian.randomusers.core.di

import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<com.hivian.randomusers.core.datasources.database.DatabaseDriverFactory> {
        com.hivian.randomusers.core.datasources.database.DatabaseDriverFactory(
            get()
        )
    }
}
