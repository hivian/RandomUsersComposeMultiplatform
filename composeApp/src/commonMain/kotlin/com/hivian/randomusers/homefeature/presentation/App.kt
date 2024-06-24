package com.hivian.randomusers.homefeature.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hivian.randomusers.core.domain.services.IUserInteractionService
import com.hivian.randomusers.homefeature.presentation.detail.DetailScreen
import com.hivian.randomusers.homefeature.presentation.home.HomeScreen
import com.hivian.randomusers.homefeature.presentation.themes.MainTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin

sealed class Screen(val route: String) {

    companion object {
        const val USER_ID_KEY = "user_id"
    }

    data object Home : Screen("home")
    data object Detail : Screen("detail/{$USER_ID_KEY}")

    fun loadParameterValue(key: String, userId: Int): String {
        return route.replace("{$key}", "$userId")
    }
}

@Composable
@Preview
fun App(
    userInteractionService: IUserInteractionService = getKoin().get()
) {
    MainTheme {
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colors.background
            ) {
                InitNavController()
                userInteractionService.snackbarHostState = snackbarHostState
            }
        }
    }
}

@Composable
fun InitNavController(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToDetail = { userId ->
                    navController.navigate(Screen.Detail.loadParameterValue(Screen.USER_ID_KEY, userId))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(Screen.USER_ID_KEY) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            DetailScreen(
                userId = backStackEntry.arguments!!.getInt(Screen.USER_ID_KEY),
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}