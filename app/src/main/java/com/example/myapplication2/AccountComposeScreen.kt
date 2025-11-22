package com.example.myapplication2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccountComposeScreen(
    accountId: String?,
    onBack: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 35.dp)) {
        Text("Profile of account №$accountId")
        Text("Name: Latysh Ivan")

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}