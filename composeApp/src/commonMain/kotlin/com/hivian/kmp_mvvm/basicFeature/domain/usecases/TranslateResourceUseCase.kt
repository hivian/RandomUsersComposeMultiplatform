package com.hivian.kmp_mvvm.basicFeature.domain.usecases

import com.hivian.kmp_mvvm.core.services.ILocalizationService
import org.jetbrains.compose.resources.StringResource

class TranslateResourceUseCase(
    private val localizationService: ILocalizationService
) {

    suspend operator fun invoke(text: StringResource): String {
        return localizationService.localizedString(text)
    }

}