package com.hivian.randomusers.core.presentation.services

import com.hivian.randomusers.core.domain.services.ILocalizationService
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

internal class LocalizationService: ILocalizationService {

    override suspend fun localizedString(key: StringResource): String {
        return getString(key)
    }

    override suspend fun localizedString(key: StringResource, vararg arguments: Any): String {
        return getString(key, *arguments)
    }

}
