package com.example.myapplication2.dto

data class UserWithSubscriptionLevel(
    val userId : Long,
    val userNickname: String,
    val daysOfSubscription: Int,
    val subscriptionLevelId: Long,
    val subscriptionLevelName: String
)