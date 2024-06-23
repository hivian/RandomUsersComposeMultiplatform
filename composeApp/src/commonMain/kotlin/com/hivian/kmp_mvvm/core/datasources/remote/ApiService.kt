package com.hivian.kmp_mvvm.core.datasources.remote

import com.hivian.kmp_mvvm.homeFeature.domain.services.IApiService
import com.hivian.kmp_mvvm.core.datasources.models.Results

internal class ApiService(
    private val httpClient: IHttpClient
): IApiService {

    companion object {
        private const val URL_PATH = "api/1.3"
        private const val SEED_PARAMETER = "seed"
        private const val PAGE_PARAMETER = "page"
        private const val RESULTS_PARAMETER = "results"
    }

    override suspend fun fetchRandomUsers(page: Int, results: Int): HttpResult<Results> {
        return httpClient.get(
            urlPath = URL_PATH,
            parameters = mapOf(
                SEED_PARAMETER to "easypeasy",
                PAGE_PARAMETER to page.toString(),
                RESULTS_PARAMETER to results.toString()
            )
        )
    }

}
