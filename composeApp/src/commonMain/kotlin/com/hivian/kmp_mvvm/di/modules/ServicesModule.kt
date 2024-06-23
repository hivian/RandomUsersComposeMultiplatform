package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.core.datasources.remote.ApiService
import com.hivian.kmp_mvvm.core.datasources.database.DatabaseService
import com.hivian.kmp_mvvm.homeFeature.domain.services.IApiService
import com.hivian.kmp_mvvm.homeFeature.domain.services.IDatabaseService
import com.hivian.kmp_mvvm.core.datasources.remote.HttpClient
import com.hivian.kmp_mvvm.core.datasources.remote.IHttpClient
import com.hivian.kmp_mvvm.core.datasources.remote.NetworkConstants
import com.hivian.kmp_mvvm.core.services.ILocalizationService
import com.hivian.kmp_mvvm.core.services.IUserInteractionService
import com.hivian.kmp_mvvm.core.services.LocalizationService
import com.hivian.kmp_mvvm.core.services.UserInteractionService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val servicesModule = module {
    single<IHttpClient> { HttpClient(NetworkConstants.BASE_URL) }
    single<IApiService> { ApiService(get()) }

    single<IDatabaseService> { DatabaseService(get(), get(named(Dispatcher.IO))) }
    single<ILocalizationService> { LocalizationService() }
    single<IUserInteractionService> { UserInteractionService() }
}
