package com.hivian.randomusers.core.domain.services

import com.hivian.randomusers.core.datasources.models.Results
import com.hivian.randomusers.core.datasources.remote.HttpResult

interface IApiService {

    suspend fun fetchRandomUsers(page: Int, results: Int): HttpResult<Results>

}
