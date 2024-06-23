package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.core.datasources.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<DatabaseDriverFactory> { DatabaseDriverFactory(get()) }
}
