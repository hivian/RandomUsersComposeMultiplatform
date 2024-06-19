package com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation

import com.hivian.kmp_mvvm.core.services.navigation.INavigationService

class NavigateBackUseCase(
    private val navigationService: INavigationService
) {

    operator fun invoke() {
        navigationService.navigateBack()
    }

}
