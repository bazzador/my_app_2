package com.example.myapplication2.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication2.AccountListItem

class AccountViewModel : ViewModel() {
    val items = mutableStateListOf<AccountListItem>()

    init {
        loadTestData()
    }

    private fun loadTestData() {
        items.addAll(
            listOf(
                AccountListItem.SectionHeader(1, "Account Info"),
                AccountListItem.InfoItem(2, "Email", "user@example.com"),
                AccountListItem.InfoItem(3, "Phone", "+380 55 123 45 67"),
                AccountListItem.InfoItem(4, "Status", "Active"),

                AccountListItem.SectionHeader(5, "Security"),
                AccountListItem.ActionItem(6, "Change password"),
                AccountListItem.ActionItem(7, "Enable 2FA"),

                AccountListItem.SectionHeader(8, "Preferences"),
                AccountListItem.InfoItem(9, "Language", "English"),
                AccountListItem.InfoItem(10, "Theme", "Light"),
                AccountListItem.SubscriptionLevelRow(
                    id = 300,
                    items = listOf("Premium", "Gold", "Silver", "Trial")
                )
            )
        )
    }
}