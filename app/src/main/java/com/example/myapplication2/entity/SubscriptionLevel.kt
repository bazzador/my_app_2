package com.example.myapplication2.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptionLevels")
data class SubscriptionLevel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)