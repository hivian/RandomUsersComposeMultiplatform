package com.hivian.kmp_mvvm.core.services.navigation

import androidx.navigation.NavHostController
import com.hivian.kmp_mvvm.basicFeature.presentation.Screen

internal class NavigationService: INavigationService {

    override lateinit var mainNavController: NavHostController

    override fun navigateBack(): Boolean = mainNavController.popBackStack()

    override fun navigateToDetailScreen(userId: Int) {
        mainNavController.navigate(Screen.Detail(userId).createRoute())
    }

}
