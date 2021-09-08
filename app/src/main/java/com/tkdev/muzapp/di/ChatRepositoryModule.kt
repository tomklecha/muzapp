package com.tkdev.muzapp.di

import com.tkdev.muzapp.common.StringWrapper
import com.tkdev.muzapp.common.StringWrapperImpl
import com.tkdev.muzapp.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ChatRepositoryModule {

    @Binds
    abstract fun bindChatRepository(impl: ChatRepositoryImpl) : ChatRepository

    @Binds
    abstract fun bindsRemoteRepository(remoteRepositoryImpl: ChatRemoteRepositoryImpl) : ChatRemoteRepository

    @Binds
    abstract fun bindsStringWrapper(stringWrapperImpl: StringWrapperImpl) : StringWrapper
}