package com.hivian.randomusers.core.domain.extensions


import com.hivian.randomusers.core.datasources.remote.ErrorType
import randomusers.composeapp.generated.resources.Res
import randomusers.composeapp.generated.resources.error_access_denied
import randomusers.composeapp.generated.resources.error_cancelled
import randomusers.composeapp.generated.resources.error_database
import randomusers.composeapp.generated.resources.error_internal
import randomusers.composeapp.generated.resources.error_no_connection
import randomusers.composeapp.generated.resources.error_not_found
import randomusers.composeapp.generated.resources.error_redirect
import randomusers.composeapp.generated.resources.error_timeout
import randomusers.composeapp.generated.resources.error_unknown
import org.jetbrains.compose.resources.StringResource

fun ErrorType.toErrorMessage(): StringResource {
    return when(this) {
        ErrorType.ACCESS_DENIED -> Res.string.error_access_denied
        ErrorType.CANCELLED -> Res.string.error_cancelled
        ErrorType.NETWORK_ERROR -> Res.string.error_no_connection
        ErrorType.TIMED_OUT -> Res.string.error_timeout
        ErrorType.NO_RESULT -> Res.string.error_not_found
        ErrorType.UNKNOWN -> Res.string.error_unknown
        ErrorType.DATABASE_ERROR -> Res.string.error_database
        ErrorType.REDIRECT -> Res.string.error_redirect
        ErrorType.INTERNAL_ERROR -> Res.string.error_internal
    }
}
