package com.example.myapplication2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication2.screen.AccountComposeScreen
import com.example.myapplication2.screen.AccountScreen
import com.example.myapplication2.screen.CounterScreen
import com.example.myapplication2.viewmodel.CounterViewModel

@Composable
fun AppNavigation(
    navController : NavHostController,
    counterViewModel: CounterViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Account.route
    ) {
        composable(Destinations.Account.route) {
            AccountScreen(
                onNavigateToAccountProfile = { id ->
                    navController.navigate(Destinations.AccountCompose.route + "/$id")
                }
            )
        }
        composable(Destinations.AccountCompose.route + "/{accountId}") {
            backStackEntry ->
            val accountId = backStackEntry.arguments?.getString("accountId")
            AccountComposeScreen(
                accountId = accountId,
                onBack = {navController.navigateUp()}
            )
        }

        composable(Destinations.Counter.route) {
            CounterScreen(counterViewModel)
        }
    }
}