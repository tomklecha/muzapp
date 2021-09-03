package com.tkdev.muzapp.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.tkdev.muzapp.databinding.ItemSentMessageBinding
import com.tkdev.muzapp.domain.models.ChatItemDomain

class MessageSentViewHolder(private val binding: ItemSentMessageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(chatItem: ChatItemDomain, checkDate: Boolean) {
        binding.apply {
            this.chatItem = chatItem
            this.dateVisibility = checkDate
        }
    }
}