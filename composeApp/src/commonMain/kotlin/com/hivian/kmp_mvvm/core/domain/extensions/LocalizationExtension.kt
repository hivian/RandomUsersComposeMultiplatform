package com.hivian.kmp_mvvm.core.domain.extensions

import com.hivian.kmp_mvvm.core.datasources.remote.ErrorType
import kmp_mvvm.composeapp.generated.resources.Res
import kmp_mvvm.composeapp.generated.resources.error_access_denied
import kmp_mvvm.composeapp.generated.resources.error_cancelled
import kmp_mvvm.composeapp.generated.resources.error_database
import kmp_mvvm.composeapp.generated.resources.error_internal
import kmp_mvvm.composeapp.generated.resources.error_no_connection
import kmp_mvvm.composeapp.generated.resources.error_not_found
import kmp_mvvm.composeapp.generated.resources.error_redirect
import kmp_mvvm.composeapp.generated.resources.error_timeout
import kmp_mvvm.composeapp.generated.resources.error_unknown
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
