package com.tkdev.muzapp.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.tkdev.muzapp.common.PreBuildWorker
import com.tkdev.muzapp.domain.dao.ChatDao
import com.tkdev.muzapp.domain.dao.UsersDao
import com.tkdev.muzapp.domain.models.ChatItemDomain
import com.tkdev.muzapp.domain.models.UserDomain


@Database(
    entities = [UserDomain::class, ChatItemDomain::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao() : UsersDao
    abstract fun chatDao() : ChatDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    ).addCallback(
                        object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                val request = OneTimeWorkRequestBuilder<PreBuildWorker>().build()
                                WorkManager.getInstance(context).enqueue(request)
                            }
                        }
                    ).build()
                    INSTANCE = instance
                    instance
                }
        }
    }
}