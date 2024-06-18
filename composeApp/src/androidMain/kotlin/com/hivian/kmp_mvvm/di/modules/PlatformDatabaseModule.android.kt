package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.core.datasources.local.getAppDatabase
import com.hivian.kmp_mvvm.core.datasources.local.AppDatabase
import org.koin.dsl.module

actual val platformDatabaseModule = module {
    single<AppDatabase> {
        getAppDatabase(get())
    }
}