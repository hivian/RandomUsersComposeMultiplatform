package com.hivian.randomusers.core.presentation.services

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import com.hivian.randomusers.core.domain.services.IUserInteractionService

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
