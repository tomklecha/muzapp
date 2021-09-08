package com.tkdev.muzapp.repository

import com.tkdev.muzapp.R
import com.tkdev.muzapp.common.Response
import com.tkdev.muzapp.common.StringWrapper
import com.tkdev.muzapp.domain.dao.ChatDao
import com.tkdev.muzapp.domain.dao.UsersDao
import com.tkdev.muzapp.domain.models.ChatItemDomain
import com.tkdev.muzapp.domain.models.UserDomain
import com.tkdev.muzapp.domain.models.mocks.MockMessages
import com.tkdev.muzapp.domain.models.mocks.MockUsers
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val chatDao: ChatDao,
    private val remoteRepository: ChatRemoteRepository,
    private val stringWrapper: StringWrapper
) : ChatRepository {

    // mocked values of users, those would be given in flow
    val currentUserId = MockUsers.currentUser.userId
    val secondUserId = MockUsers.secondUser.userId

    override suspend fun sendMessageToUser(message: String, chatId: String) : Response =
        try {
            val messageId = stringWrapper.generateRandomId()
            val chatItem = ChatItemDomain(
                messageId,
                chatId,
                currentUserId,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(MockMessages.DATE_FORMAT)),
                message,
                false
            )
            chatDao.insertChatMessage(chatItem)
            Response.SUCCESS(messageId)
        }
        catch (e: Exception) {
            Response.FAIL
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
                stringWrapper.generateRandomId(),
                chatId,
                secondUserId,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(MockMessages.DATE_FORMAT)),
                stringWrapper.getStringMessage(R.string.message_random_generated, timeMessageGeneration),
                false
            )
        )
        remoteRepository.setAllSendMessageAsRead()
    }

    override suspend fun awaitResponse(messageId: String) {
        remoteRepository.sendMessageRequest(messageId)
    }

}