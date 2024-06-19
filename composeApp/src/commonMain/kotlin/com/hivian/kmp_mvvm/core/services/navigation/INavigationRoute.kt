package com.hivian.kmp_mvvm.core.services.navigation

import androidx.navigation.NamedNavArgument

interface INavigationRoute {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

}