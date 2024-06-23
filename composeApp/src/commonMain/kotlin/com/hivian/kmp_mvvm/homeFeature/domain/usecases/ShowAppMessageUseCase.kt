package com.hivian.kmp_mvvm.homeFeature.domain.usecases

import androidx.compose.material.SnackbarDuration
import com.hivian.kmp_mvvm.core.domain.services.IUserInteractionService

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
