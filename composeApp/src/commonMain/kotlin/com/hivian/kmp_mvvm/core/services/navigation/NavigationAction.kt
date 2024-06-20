package com.hivian.kmp_mvvm.core.services.navigation

sealed class NavigationAction {
    data object ToHomeScreen : NavigationAction()
    data class ToDetailScreen(val userId: Int) : NavigationAction()
    data object Back : NavigationAction()
}
