package com.hivian.randomusers.core.datasources

import com.hivian.randomusers.core.datasources.remote.ErrorType

sealed class ServiceResult<out T: Any> {

    data class Success<out T : Any>(val data: T) : ServiceResult<T>()

    data class Error<out T : Any>(val errorType: ErrorType, val data: T? = null) : ServiceResult<T>()

}
