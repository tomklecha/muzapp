package com.tkdev.muzapp.repository

import com.tkdev.muzapp.common.CoroutineContextProvider
import com.tkdev.muzapp.domain.dao.ChatDao
import com.tkdev.muzapp.domain.models.ResponseJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

private const val MESSAGE_DELAY = 20000L

@Singleton
class ChatRemoteRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val coroutineContextProvider: CoroutineContextProvider
) : ChatRemoteRepository {

    private val readMessageJobs: MutableList<ResponseJob> = mutableListOf()

    override suspend fun sendMessageRequest(messageId: String) {
        val job: Job = CoroutineScope(coroutineContextProvider.Io + Job()).launch {
            delay(MESSAGE_DELAY)
        }
        job.invokeOnCompletion {
            chatDao.setMessageRead(messageId)
        }
        val responseJob = ResponseJob(messageId, job)
        readMessageJobs.add(responseJob)
    }

    override fun setAllSendMessageAsRead() {
        readMessageJobs.forEach { readMessageJob ->
            readMessageJob.job.cancel()
            chatDao.setMessageRead(readMessageJob.id)
        }
        readMessageJobs.clear()
    }
}