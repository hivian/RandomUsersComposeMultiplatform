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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hivian.kmp_mvvm.basicFeature.domain.usecases.navigation.BasicFeatureScreen
import com.hivian.kmp_mvvm.basicFeature.presentation.detail.DetailScreen
import com.hivian.kmp_mvvm.core.services.IUserInteractionService
import com.hivian.kmp_mvvm.core.services.navigation.INavigationService
import com.hivian.kmp_mvvm.basicFeature.presentation.home.HomeScreen
import com.hivian.kmp_mvvm.basicFeature.presentation.themes.MainTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.getKoin

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
    navigationService: INavigationService = getKoin().get()
) {
    navigationService.mainNavController = rememberNavController()

    NavHost(
        navController = navigationService.mainNavController,
        startDestination = BasicFeatureScreen.Home.name,
    ) {
        composable(route = BasicFeatureScreen.Home.name) {
            HomeScreen()
        }
        composable(route = BasicFeatureScreen.Detail.name) {
            DetailScreen()
        }
    }
}