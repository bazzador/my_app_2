package com.example.myapplication2.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun AccountScreen(
    onNavigateToAccountProfile: (String) -> Unit
) {
    var accountText by rememberSaveable { mutableStateOf("") }
    val accountID = "44416682"

    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp)) {
        Text(text = accountText)
        Button(
            onClick = {
                accountText = "Your account number: $accountID"
            },
        ) { Text (text = "Show your account number")  }
        Button(
            onClick = {
                onNavigateToAccountProfile(accountID)
            },
        ) { Text( text = "Go to account profile")}
    }
}