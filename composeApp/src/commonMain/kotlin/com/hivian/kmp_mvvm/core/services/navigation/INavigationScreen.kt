package com.hivian.kmp_mvvm.core.services.navigation

import androidx.navigation.NamedNavArgument

interface INavigationScreen {

    val name: String

}

interface INavigationScreenArgs: INavigationScreen {

    val arguments: List<NamedNavArgument>

}
