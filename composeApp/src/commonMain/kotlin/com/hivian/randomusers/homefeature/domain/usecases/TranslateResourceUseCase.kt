package com.hivian.randomusers.homefeature.domain.usecases

import com.hivian.randomusers.core.domain.services.ILocalizationService
import org.jetbrains.compose.resources.StringResource

class TranslateResourceUseCase(
    private val localizationService: ILocalizationService
) {

    suspend operator fun invoke(text: StringResource): String {
        return localizationService.localizedString(text)
    }

}