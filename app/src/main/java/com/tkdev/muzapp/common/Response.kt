package com.tkdev.muzapp.common

sealed class Response {
    data class SUCCESS (val messageId : String) : Response()
    object FAIL : Response()

}