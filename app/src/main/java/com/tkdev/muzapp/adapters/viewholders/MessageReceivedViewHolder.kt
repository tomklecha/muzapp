package com.tkdev.muzapp.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.tkdev.muzapp.databinding.ItemReceivedMessageBinding
import com.tkdev.muzapp.domain.models.ChatItemDomain

class MessageReceivedViewHolder(private val binding: ItemReceivedMessageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(chatItem: ChatItemDomain, checkDate: Boolean) {
        binding.apply {
            this.chatItem = chatItem
            this.dateVisibility = checkDate
        }
    }
}