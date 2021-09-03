package com.tkdev.muzapp.domain.models.mocks

import com.tkdev.muzapp.domain.models.ChatItemDomain

// this variable would be used in real life app do distinguish between multiple chats
// dd/MM/yy HH:mm

object MockMessages {
    const val CHAT_ID = "chat_id"
    const val DATE_FORMAT = "dd/MM/yy HH:mm:ss"
    const val SHORT_DATE = "EEEE HH:mm"

    val firstMessage = ChatItemDomain(
        "message_first_id",
        CHAT_ID,
        MockUsers.secondUser.userId,
        "30/08/21 10:00:50",
        "Wowsa sounds fun! !"
    )
    val secondMessage = ChatItemDomain(
        "message_second_id",
        CHAT_ID,
        MockUsers.secondUser.userId,
        "30/08/21 11:59:00",
        "Yeah, for sure that works. What time do you think?"
    )
    val thirdMessage = ChatItemDomain(
        "message_third_id",
        CHAT_ID,
        MockUsers.currentUser.userId,
        "01/09/21 11:59:15",
        "Does 7pm work for you? I've got to pick up my little brother first from a party"
    )
    val fourthMessage = ChatItemDomain(
        "message_fourth_id",
        CHAT_ID,
        MockUsers.currentUser.userId,
        "01/09/21 12:00:00",
        "What are you up to today?"
    )
    val fifthMessage = ChatItemDomain(
        "message_fifth_id",
        CHAT_ID,
        MockUsers.secondUser.userId,
        "01/09/21 12:01:00",
        "Nothing much"
    )
    val sixthMessage = ChatItemDomain(
        "message_sixth_id",
        CHAT_ID,
        MockUsers.secondUser.userId,
        "01/09/21 12:02:00",
        "Actually just about to go to the shopping, got any recommendations for a good shoe shop? I'm a fashion disaster"
    )
    val seventhMessage = ChatItemDomain(
        "message_seventh_id",
        CHAT_ID,
        MockUsers.secondUser.userId,
        "01/09/21 13:03:00",
        "The last hour went on for hours"
    )

    val mockChat = listOf(
        firstMessage,
        secondMessage,
        thirdMessage,
        fourthMessage,
        fifthMessage,
        sixthMessage,
        seventhMessage
    )
}