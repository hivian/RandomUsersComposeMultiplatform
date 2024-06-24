package com.hivian.randomusers.homefeature.domain.usecases

import androidx.compose.material.SnackbarDuration
import com.hivian.randomusers.core.domain.services.IUserInteractionService

class ShowAppMessageUseCase(
    private val userInteractionService: IUserInteractionService
) {

    suspend operator fun invoke(message: String) {
        userInteractionService.showSnackbar(
            snackbarDuration = SnackbarDuration.Short,
            message = message
        )
    }

}
