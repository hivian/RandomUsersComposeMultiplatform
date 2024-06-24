package com.hivian.randomusers.core.di

import com.hivian.randomusers.core.datasources.database.DatabaseService
import com.hivian.randomusers.core.datasources.remote.ApiService
import com.hivian.randomusers.core.datasources.remote.HttpClient
import com.hivian.randomusers.core.datasources.remote.NetworkConstants
import com.hivian.randomusers.core.domain.services.IApiService
import com.hivian.randomusers.core.domain.services.IDatabaseService
import com.hivian.randomusers.core.domain.services.IHttpClient
import com.hivian.randomusers.core.domain.services.ILocalizationService
import com.hivian.randomusers.core.domain.services.IUserInteractionService
import com.hivian.randomusers.core.presentation.services.LocalizationService
import com.hivian.randomusers.core.presentation.services.UserInteractionService
import org.koin.dsl.module

val servicesModule = module {
    single<IHttpClient> { HttpClient(NetworkConstants.BASE_URL) }
    single<IApiService> { ApiService(get()) }
    single<IDatabaseService> { DatabaseService(get()) }
    single<ILocalizationService> { LocalizationService() }
    single<IUserInteractionService> { UserInteractionService() }
}
