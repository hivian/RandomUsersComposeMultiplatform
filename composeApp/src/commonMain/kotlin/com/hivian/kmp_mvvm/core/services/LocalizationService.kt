package com.hivian.kmp_mvvm.core.services

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
