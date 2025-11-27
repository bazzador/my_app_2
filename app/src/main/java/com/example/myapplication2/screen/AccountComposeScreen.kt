package com.example.myapplication2.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication2.AccountListItem
import com.example.myapplication2.ui.theme.ActionItemView
import com.example.myapplication2.ui.theme.SubscriptionLevelRowView
import com.example.myapplication2.ui.theme.InfoItemView
import com.example.myapplication2.ui.theme.SectionHeaderView
import com.example.myapplication2.viewmodel.AccountViewModel

@Composable
fun AccountComposeScreen(
    accountId: String?,
    onBack: () -> Unit,
    viewModel: AccountViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 35.dp)
            .fillMaxSize()
    ) {

        Text("Profile of account №$accountId")
        Text("Name: Latysh Ivan")

        Button(
            onClick = onBack,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Text("Back")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(
                items = viewModel.items,
                key = { item ->
                    when (item) {
                        is AccountListItem.SectionHeader -> item.id
                        is AccountListItem.InfoItem -> item.id
                        is AccountListItem.ActionItem -> item.id
                        is AccountListItem.SubscriptionLevelRow -> item.id
                    }
                }
            ) { item ->
                when (item) {
                    is AccountListItem.SectionHeader -> SectionHeaderView(item)
                    is AccountListItem.InfoItem -> InfoItemView(item)
                    is AccountListItem.ActionItem -> ActionItemView(item)
                    is AccountListItem.SubscriptionLevelRow -> SubscriptionLevelRowView(item)
                }
            }
        }
    }
}