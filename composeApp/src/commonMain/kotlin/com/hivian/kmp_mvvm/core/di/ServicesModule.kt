package com.hivian.kmp_mvvm.core.di

import com.hivian.kmp_mvvm.core.datasources.remote.ApiService
import com.hivian.kmp_mvvm.core.datasources.database.DatabaseService
import com.hivian.kmp_mvvm.core.domain.services.IApiService
import com.hivian.kmp_mvvm.core.domain.services.IDatabaseService
import com.hivian.kmp_mvvm.core.datasources.remote.HttpClient
import com.hivian.kmp_mvvm.core.domain.services.IHttpClient
import com.hivian.kmp_mvvm.core.datasources.remote.NetworkConstants
import com.hivian.kmp_mvvm.core.domain.services.ILocalizationService
import com.hivian.kmp_mvvm.core.domain.services.IUserInteractionService
import com.hivian.kmp_mvvm.core.presentation.services.LocalizationService
import com.hivian.kmp_mvvm.core.presentation.services.UserInteractionService
import org.koin.dsl.module

val servicesModule = module {
    single<IHttpClient> { HttpClient(NetworkConstants.BASE_URL) }
    single<IApiService> { ApiService(get()) }
    single<IDatabaseService> { DatabaseService(get()) }
    single<ILocalizationService> { LocalizationService() }
    single<IUserInteractionService> { UserInteractionService() }
}
