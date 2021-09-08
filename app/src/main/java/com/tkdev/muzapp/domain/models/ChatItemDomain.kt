package com.tkdev.muzapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_chat")
data class ChatItemDomain(
    @PrimaryKey @ColumnInfo(name = "message_id") val messageId: String,
    @ColumnInfo(name = "chat_id") val chatId: String,
    @ColumnInfo(name = "send_by") val sendByUserId: String,
    @ColumnInfo(name = "timestamp") val messageTimestamp: String,
    @ColumnInfo(name = "message") val chatMessage: String,
    @ColumnInfo(name = "was_it_read") val wasItRead : Boolean
)