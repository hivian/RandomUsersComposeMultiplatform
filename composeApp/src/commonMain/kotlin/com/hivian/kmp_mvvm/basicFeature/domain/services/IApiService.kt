package com.hivian.kmp_mvvm.basicFeature.domain.services

import com.hivian.kmp_mvvm.core.datasources.models.Results
import com.hivian.kmp_mvvm.core.datasources.remote.HttpResult

interface IApiService {

    suspend fun fetchRandomUsers(page: Int, results: Int): HttpResult<Results>

}
