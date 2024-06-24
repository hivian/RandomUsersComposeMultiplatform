package com.hivian.randomusers.core.domain.usescases

import com.hivian.randomusers.core.domain.services.ILocalizationService
import org.jetbrains.compose.resources.StringResource

class LocalizationUseCase(
    private val localizationService: ILocalizationService
) {

    suspend operator fun invoke(key: StringResource) : String {
        return localizationService.localizedString(key)
    }

}