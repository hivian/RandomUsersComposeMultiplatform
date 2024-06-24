package com.hivian.randomusers.core.datasources.remote

import com.hivian.randomusers.core.datasources.models.Results
import com.hivian.randomusers.core.domain.services.IApiService
import com.hivian.randomusers.core.domain.services.IHttpClient
import com.hivian.randomusers.core.domain.services.get

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
