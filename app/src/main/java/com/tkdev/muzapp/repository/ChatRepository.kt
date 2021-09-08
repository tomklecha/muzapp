package com.tkdev.muzapp.repository

import com.tkdev.muzapp.common.Response
import com.tkdev.muzapp.domain.models.ChatItemDomain
import com.tkdev.muzapp.domain.models.UserDomain
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun sendMessageToUser(message: String, chatId: String): Response

    fun fetchCurrentUser(): Flow<UserDomain>

    fun fetchChatMessages(chatId: String): Flow<List<ChatItemDomain>>

    suspend fun prepopulateData()

    fun fetchSecondUser(): Flow<UserDomain>

    suspend fun clearChat()

    suspend fun sendMessageAsRecipient(chatId: String)

    suspend fun awaitResponse(messageId: String)

}