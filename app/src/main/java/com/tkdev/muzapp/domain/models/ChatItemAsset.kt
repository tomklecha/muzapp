package com.tkdev.muzapp.domain.models

import com.google.gson.annotations.SerializedName

data class ChatItemAsset(
    @SerializedName("message_id") val messageId: String,
    @SerializedName("chat_id") val chatId: String,
    @SerializedName("send_by") val sendByUserId: String,
    @SerializedName("timestamp") val messageTimestamp: String,
    @SerializedName("message") val chatMessage: String
)