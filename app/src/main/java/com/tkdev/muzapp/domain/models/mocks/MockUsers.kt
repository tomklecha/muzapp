package com.tkdev.muzapp.domain.models.mocks

import com.tkdev.muzapp.domain.models.UserDomain

object MockUsers {
    val currentUser = UserDomain(
        "current_user_id",
        "Tomasz"
    )
    val secondUser = UserDomain(
        "second_user_id",
        "Sarah"
    )
}