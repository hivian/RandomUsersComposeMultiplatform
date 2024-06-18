package com.hivian.kmp_mvvm.di.modules

import com.hivian.kmp_mvvm.basicFeature.data.sources.ApiService
import com.hivian.kmp_mvvm.basicFeature.domain.services.IApiService
import com.hivian.kmp_mvvm.core.datasources.remote.HttpClient
import com.hivian.kmp_mvvm.core.datasources.remote.IHttpClient
import com.hivian.kmp_mvvm.core.datasources.remote.NetworkConstants
import org.koin.dsl.module

val networkModule = module {
    single<IHttpClient> { HttpClient(NetworkConstants.BASE_URL) }
    single<IApiService> { ApiService(get()) }
}
