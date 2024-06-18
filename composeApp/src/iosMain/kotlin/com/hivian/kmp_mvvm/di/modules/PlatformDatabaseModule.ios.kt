package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.core.datasources.database.getAppDatabase
import com.hivian.kmp_mvvm.core.datasources.database.AppDatabase
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<AppDatabase> {
        getAppDatabase()
    }
}