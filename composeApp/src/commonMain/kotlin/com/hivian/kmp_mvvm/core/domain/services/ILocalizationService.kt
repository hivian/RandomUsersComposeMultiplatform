package com.hivian.kmp_mvvm.core.domain.services

import org.jetbrains.compose.resources.StringResource

interface ILocalizationService {

    suspend fun localizedString(key: StringResource): String

    suspend fun localizedString(key: StringResource, vararg arguments: Any): String

}
