package com.tkdev.muzapp.domain.dao

import androidx.room.*
import com.tkdev.muzapp.domain.models.ChatItemDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Query("SELECT * FROM table_chat WHERE chat_id IS :chatId")
    fun getChatItems(chatId: String) : Flow<List<ChatItemDomain>>

    @Insert
    fun insertChatMessage(chatItemDomain: ChatItemDomain)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun prepopulateData(chatData : List<ChatItemDomain>)

    @Query("DELETE FROM table_chat")
    suspend fun deleteAllMessages()
}