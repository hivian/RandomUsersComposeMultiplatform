package com.hivian.kmp_mvvm.core.services.navigation

import androidx.navigation.NavHostController

interface INavigationService {

    var mainNavController: NavHostController

    fun navigateBack(): Boolean

    fun navigateToDetailScreen(userId: Int)

}
