package com.tkdev.muzapp.di

import android.content.Context
import com.tkdev.muzapp.domain.AppDatabase
import com.tkdev.muzapp.domain.dao.ChatDao
import com.tkdev.muzapp.domain.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppDbModule {


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideUsersDao(appDb: AppDatabase): UsersDao = appDb.usersDao()

    @Provides
    fun provideChatDao(appDb: AppDatabase): ChatDao = appDb.chatDao()
//
//    @Provides
//    fun provideTimeScheduleDao(appDb: AppDb): TimeScheduleDao = appDb.timeScheduleDao()
//
//    @Provides
//    fun provideVenueDao(appDb: AppDb): VenueDao = appDb.venueDao()
//
//    @Provides
//    fun provideWorkshopDao(appDb: AppDb): WorkshopsDao = appDb.workshopsDao()
}