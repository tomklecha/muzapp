package com.tkdev.muzapp.di

import com.tkdev.muzapp.repository.ChatRepository
import com.tkdev.muzapp.repository.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ChatRepositoryModule {

    @Binds
    abstract fun bindChatRepository(impl: ChatRepositoryImpl) : ChatRepository
}