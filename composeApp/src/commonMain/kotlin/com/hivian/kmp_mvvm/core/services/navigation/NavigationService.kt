package com.hivian.kmp_mvvm.core.services.navigation

import androidx.navigation.NavHostController

internal class NavigationService: INavigationService {

    override lateinit var mainNavController: NavHostController

    override fun navigateBack(): Boolean = mainNavController.popBackStack()

    override fun navigateTo(route: String) {
        mainNavController.navigate(route)
    }

}
