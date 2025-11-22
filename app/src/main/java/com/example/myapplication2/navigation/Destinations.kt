package com.example.myapplication2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Account : Destinations("account", "Account", Icons.Default.Home)
    object Counter : Destinations("counter", "Counter", Icons.Default.Add)

    object AccountCompose: Destinations("account_compose", "Compose", Icons.Default.AccountCircle)
}

val mainScreens = listOf(
    Destinations.Account,
    Destinations.Counter
)