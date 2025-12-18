package com.example.myapplication2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication2.dao.SubscriptionLevelDao
import com.example.myapplication2.dao.UserDao
import com.example.myapplication2.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

@Database(entities = [User::class, com.example.myapplication2.entity.SubscriptionLevel::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun subscriptionLevelDao(): SubscriptionLevelDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "user_item_database"
                ).addCallback(UserItemCallBack(scope)).build()

                INSTANCE = instance
                return instance
            }
        }

        private class UserItemCallBack(val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(scope.coroutineContext + Dispatchers.IO).launch {
                    INSTANCE?.let { database ->
                        scope.launch {
                            database.subscriptionLevelDao()
                                .insert(com.example.myapplication2.entity.SubscriptionLevel(0, "VIP"))
                            database.subscriptionLevelDao()
                                .insert(com.example.myapplication2.entity.SubscriptionLevel(1, "Premium"))

                            database.userDao().insert(User(0, "JohnY462", 10, 0 ))
                            database.userDao().insert(User(0, "Jan3", 3, 0 ))
                            database.userDao().insert(User(0, "Koenbart", 37, 1 ))

                        }
                    }
                }
            }
        }
    }
}