package com.hivian.randomusers.core.di

import com.hivian.randomusers.core.datasources.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<DatabaseDriverFactory> { DatabaseDriverFactory() }
}