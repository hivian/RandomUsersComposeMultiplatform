package com.hivian.randomusers.core.domain.extensions


import com.hivian.randomusers.core.datasources.remote.ErrorType
import org.jetbrains.compose.resources.StringResource

import randomuserscomposemultiplatform.composeapp.generated.resources.Res
import randomuserscomposemultiplatform.composeapp.generated.resources.error_access_denied
import randomuserscomposemultiplatform.composeapp.generated.resources.error_cancelled
import randomuserscomposemultiplatform.composeapp.generated.resources.error_database
import randomuserscomposemultiplatform.composeapp.generated.resources.error_internal
import randomuserscomposemultiplatform.composeapp.generated.resources.error_no_connection
import randomuserscomposemultiplatform.composeapp.generated.resources.error_not_found
import randomuserscomposemultiplatform.composeapp.generated.resources.error_redirect
import randomuserscomposemultiplatform.composeapp.generated.resources.error_timeout
import randomuserscomposemultiplatform.composeapp.generated.resources.error_unknown

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
