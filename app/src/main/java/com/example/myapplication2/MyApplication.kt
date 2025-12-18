package com.example.myapplication2

import android.app.Application
import com.example.myapplication2.db.MyDatabase
import com.example.myapplication2.repository.SubscriptionLevelRepository
import com.example.myapplication2.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class MyApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val database by lazy { MyDatabase.getDatabase(this, applicationScope)}
    val userRepository by lazy { UserRepository(database.userDao()) }
    val subscriptionLevelRepository by lazy { SubscriptionLevelRepository(database.subscriptionLevelDao()) }
}