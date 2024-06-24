package com.hivian.kmp_mvvm.core.domain.services

import com.hivian.kmp_mvvm.core.datasources.remote.HttpResult
import kotlin.reflect.KClass


interface IHttpClient {

    suspend fun <T : Any> get(urlPath: String, ofClass: KClass<T>, parameters: Map<String, String>? = null): HttpResult<T>

}

suspend inline fun <reified T : Any> IHttpClient.get(
    urlPath: String,
    parameters: Map<String, String>?
): HttpResult<T> = get(urlPath, T::class, parameters)
