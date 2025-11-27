package com.example.myapplication2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication2.navigation.AppNavigation
import com.example.myapplication2.navigation.Destinations
import com.example.myapplication2.navigation.mainScreens
import com.example.myapplication2.ui.theme.MyApplication2Theme
import com.example.myapplication2.viewmodel.CounterViewModel

class MainActivity : ComponentActivity() {

    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication2Theme {
                MyApplication2App(counterViewModel)
            }
        }
    }
}

@Composable
fun MyApplication2App(counterViewModel: CounterViewModel) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Destinations.Account.route

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            mainScreens.forEach { screen ->
                item(
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(screen.icon, contentDescription = screen.label)},
                    label = { Text(screen.label)}
                )
            }
        }
    ) {
        AppNavigation(navController, counterViewModel)
    }
}