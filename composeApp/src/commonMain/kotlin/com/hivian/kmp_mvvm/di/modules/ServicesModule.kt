package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.basicFeature.data.sources.ApiService
import com.hivian.kmp_mvvm.basicFeature.data.sources.DatabaseService
import com.hivian.kmp_mvvm.basicFeature.domain.services.IApiService
import com.hivian.kmp_mvvm.basicFeature.domain.services.IDatabaseService
import com.hivian.kmp_mvvm.core.datasources.remote.HttpClient
import com.hivian.kmp_mvvm.core.datasources.remote.IHttpClient
import com.hivian.kmp_mvvm.core.datasources.remote.NetworkConstants
import com.hivian.kmp_mvvm.core.services.ILocalizationService
import com.hivian.kmp_mvvm.core.services.IUserInteractionService
import com.hivian.kmp_mvvm.core.services.LocalizationService
import com.hivian.kmp_mvvm.core.services.UserInteractionService
import org.koin.dsl.module

val servicesModule = module {
    single<IHttpClient> { HttpClient(NetworkConstants.BASE_URL) }
    single<IApiService> { ApiService(get()) }
    single<IDatabaseService> { DatabaseService(get()) }
    single<ILocalizationService> { LocalizationService() }
    single<IUserInteractionService> { UserInteractionService() }
}
