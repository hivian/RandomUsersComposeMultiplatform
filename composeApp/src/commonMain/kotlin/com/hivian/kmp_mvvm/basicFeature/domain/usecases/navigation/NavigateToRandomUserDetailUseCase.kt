package com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation

import com.hivian.kmp_mvvm.core.services.navigation.INavigationService

class NavigateToRandomUserDetailUseCase(
    private val navigationService: INavigationService
) {

    operator fun invoke(userId: Int) {
        navigationService.navigateTo(BasicFeatureScreen.Detail.createRouteWithArgs(userId))
    }

}