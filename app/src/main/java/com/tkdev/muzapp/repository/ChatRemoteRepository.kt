package com.tkdev.muzapp.repository

interface ChatRemoteRepository {

    suspend fun sendMessageRequest(messageId: String)

    fun setAllSendMessageAsRead()
}