package com.tkdev.muzapp.adapters.viewholders

import android.util.Log

import androidx.recyclerview.widget.DiffUtil
import com.tkdev.muzapp.domain.models.ChatItemDomain

class ChatItemDiffUtil : DiffUtil.ItemCallback<ChatItemDomain>() {
    override fun areItemsTheSame(oldItem: ChatItemDomain, newItem: ChatItemDomain): Boolean {
        return oldItem.messageId == newItem.messageId
    }

    override fun areContentsTheSame(oldItem: ChatItemDomain, newItem: ChatItemDomain): Boolean =
        oldItem == newItem
}


