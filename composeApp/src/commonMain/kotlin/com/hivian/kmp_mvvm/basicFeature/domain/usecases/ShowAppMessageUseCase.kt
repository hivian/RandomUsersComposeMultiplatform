package com.hivian.kmp_mvvm.basicFeature.domain.usecases

import androidx.compose.material.SnackbarDuration
import com.hivian.kmp_mvvm.core.services.IUserInteractionService

class ShowAppMessageUseCase constructor(
    private val userInteractionService: IUserInteractionService
) {

    suspend operator fun invoke(message: String) {
        userInteractionService.showSnackbar(
            snackbarDuration = SnackbarDuration.Short,
            message = message
        )
    }

}
