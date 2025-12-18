package com.example.myapplication2.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(
            entity = SubscriptionLevel::class,
            parentColumns = ["id"],
            childColumns = ["subscriptionLevelId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("subscriptionLevelId")]
)

data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nickname: String,
    val daysOfSubscription: Int,
    val subscriptionLevelId: Long
)