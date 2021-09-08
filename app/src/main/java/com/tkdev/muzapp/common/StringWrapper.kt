package com.tkdev.muzapp.common

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

interface StringWrapper {

    fun getStringMessage(@StringRes resId: Int): String

    fun getStringMessage(@StringRes resId: Int, vararg formatArgs: Any): String

    fun generateRandomId(): String
}

@Singleton
class StringWrapperImpl @Inject constructor(@ApplicationContext private val context: Context) :
    StringWrapper {

    override fun getStringMessage(resId: Int): String = context.getString(resId)

    override fun getStringMessage(resId: Int, vararg formatArgs: Any): String =
        context.getString(resId, *formatArgs)

    override fun generateRandomId() = UUID.randomUUID().toString()


}