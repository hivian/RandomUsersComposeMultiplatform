package com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hivian.kmp_mvvm.core.services.navigation.INavigationScreen
import com.hivian.kmp_mvvm.core.services.navigation.INavigationScreenArgs

sealed class BasicFeatureScreen {

    object Home : INavigationScreen {

        override val name: String = "home"

    }

     object Detail : INavigationScreenArgs {

         private const val BASE_ROUTE: String = "detail"
         private const val USER_ID_KEY = "userId"

         override val name: String = "$BASE_ROUTE/{$USER_ID_KEY}"

         override val arguments: List<NamedNavArgument> =
            listOf(navArgument(USER_ID_KEY) { type = NavType.IntType })

         fun createRouteWithArgs(userId: Int): String {
             return "$BASE_ROUTE/$userId"
         }

    }

}
