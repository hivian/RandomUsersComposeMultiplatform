package com.hivian.kmp_mvvm.core.services

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState

internal class UserInteractionService: IUserInteractionService {

    override lateinit var snackbarHostState: SnackbarHostState

    override suspend fun showSnackbar(
        snackbarDuration: SnackbarDuration,
        message: String,
        actionTitle: String?
    ) {
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionTitle,
            duration = snackbarDuration,
        )
    }

}
