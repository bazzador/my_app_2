package com.example.myapplication2

sealed class AccountListItem {
    data class InfoItem(
        val id: Int,
        val title: String,
        val value: String
    ) : AccountListItem()

    data class SectionHeader(
        val id: Int,
        val title: String
    ) : AccountListItem()

    data class ActionItem(
        val id: Int,
        val title: String
    ) : AccountListItem()

    data class SubscriptionLevelRow(
        val id: Int,
        val items: List<String>
    ) : AccountListItem()
}