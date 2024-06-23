package com.hivian.kmp_mvvm.core.domain.services

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState

interface IUserInteractionService {

    var snackbarHostState: SnackbarHostState

    suspend fun showSnackbar(
        snackbarDuration: SnackbarDuration,
        message: String,
        actionTitle: String? = null
    )

}
