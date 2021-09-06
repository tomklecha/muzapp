package com.tkdev.muzapp.repository

import com.tkdev.muzapp.domain.dao.ChatDao
import com.tkdev.muzapp.domain.dao.UsersDao
import com.tkdev.muzapp.domain.models.ChatItemDomain
import com.tkdev.muzapp.domain.models.UserDomain
import com.tkdev.muzapp.domain.models.mocks.MockMessages
import com.tkdev.muzapp.domain.models.mocks.MockUsers
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val chatDao: ChatDao
) : ChatRepository {

    // mocked values of users, those would be given in flow
    val currentUserId = MockUsers.currentUser.userId
    val secondUserId = MockUsers.secondUser.userId

    override suspend fun sendMessageToUser(message: String, chatId: String) {
        chatDao.insertChatMessage(
            ChatItemDomain(
                UUID.randomUUID().toString(),
                chatId,
                currentUserId,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(MockMessages.DATE_FORMAT)),
                message,
                false
            )
        )
    }

    override fun fetchCurrentUser(): Flow<UserDomain> =
        usersDao.fetchUser(currentUserId)

    override fun fetchSecondUser(): Flow<UserDomain> =
        usersDao.fetchUser(secondUserId)

    override fun fetchChatMessages(chatId: String): Flow<List<ChatItemDomain>> =
        chatDao.getChatItems(chatId)

    override suspend fun prepopulateData() {
        chatDao.prepopulateData(MockMessages.mockChat)
    }

    override suspend fun clearChat() {
        chatDao.deleteAllMessages()
    }

    override suspend fun sendMessageAsRecipient(chatId: String) {
        val timeMessageGeneration = LocalDateTime.now().format(DateTimeFormatter.ofPattern(MockMessages.DATE_FORMAT))
        chatDao.insertChatMessage(
            ChatItemDomain(
                UUID.randomUUID().toString(),
                chatId,
                secondUserId,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(MockMessages.DATE_FORMAT)),
                "Random message generated at $timeMessageGeneration",
                false
            )
        )
    }

}