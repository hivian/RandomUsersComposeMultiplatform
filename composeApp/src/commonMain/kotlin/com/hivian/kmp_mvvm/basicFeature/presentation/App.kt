package com.hivian.kmp_mvvm.basicFeature.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hivian.kmp_mvvm.basicFeature.presentation.detail.DetailScreen
import com.hivian.kmp_mvvm.core.services.IUserInteractionService
import com.hivian.kmp_mvvm.basicFeature.presentation.home.HomeScreen
import com.hivian.kmp_mvvm.basicFeature.presentation.themes.MainTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.getKoin

sealed class Screen(val route: String) {

    companion object {
        const val USER_ID_PARAMETER = "user_id"
    }

    data object Home : Screen("home")
    data object Detail : Screen("detail/{$USER_ID_PARAMETER}")
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
                    navController.navigate(Screen.Detail.route.replace(Screen.USER_ID_PARAMETER, "$userId"))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(Screen.USER_ID_PARAMETER) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            DetailScreen(
                userId = backStackEntry.arguments!!.getInt(Screen.USER_ID_PARAMETER),
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}