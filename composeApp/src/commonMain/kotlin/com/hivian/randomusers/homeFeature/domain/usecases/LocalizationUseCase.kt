package com.hivian.randomusers.homeFeature.domain.usecases

import com.hivian.randomusers.core.domain.services.ILocalizationService
import org.jetbrains.compose.resources.StringResource

class LocalizationUseCase(
    private val localizationService: ILocalizationService
) {

    suspend operator fun invoke(key: StringResource) : String {
        return localizationService.localizedString(key)
    }

}