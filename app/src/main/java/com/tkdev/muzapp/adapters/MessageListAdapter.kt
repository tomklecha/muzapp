package com.tkdev.muzapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.muzapp.adapters.viewholders.ChatItemDiffUtil
import com.tkdev.muzapp.adapters.viewholders.MessageReceivedViewHolder
import com.tkdev.muzapp.adapters.viewholders.MessageSentViewHolder
import com.tkdev.muzapp.databinding.ItemReceivedMessageBinding
import com.tkdev.muzapp.databinding.ItemSentMessageBinding
import com.tkdev.muzapp.domain.models.ChatItemDomain
import com.tkdev.muzapp.domain.models.UserDomain
import com.tkdev.muzapp.domain.models.mocks.MockMessages
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val MESSAGE_SENT_BY_USER = 1
private const val MESSAGE_RECEIVED_BY_USER = 2

class MessageListAdapter(private val currentUserDomain: UserDomain) :
    ListAdapter<ChatItemDomain, RecyclerView.ViewHolder>(ChatItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            MESSAGE_SENT_BY_USER -> MessageSentViewHolder(
                ItemSentMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> MessageReceivedViewHolder(
                ItemReceivedMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                val messageHolder = holder as MessageSentViewHolder
                messageHolder.bind(currentList[position], checkDate(position))

            }
            else -> {
                val messageHolder = holder as MessageReceivedViewHolder
                messageHolder.bind(currentList[position], checkDate(position))
            }

        }
    }

    private fun checkDate(position: Int): Boolean {
        if (position == 0)
            return true
        val dateFormat = DateTimeFormatter.ofPattern(MockMessages.DATE_FORMAT)
        val currentDate = LocalDateTime.parse(currentList[position].messageTimestamp, dateFormat)
        val previousDate =
            LocalDateTime.parse(currentList[position - 1].messageTimestamp, dateFormat)
        return currentDate.minusHours(1L).isAfter(previousDate)
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position].sendByUserId == currentUserDomain.userId) {
            true -> MESSAGE_SENT_BY_USER
            false -> MESSAGE_RECEIVED_BY_USER
        }
}

