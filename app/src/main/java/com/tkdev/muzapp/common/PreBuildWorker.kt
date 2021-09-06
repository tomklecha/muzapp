package com.tkdev.muzapp.common

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.tkdev.muzapp.domain.AppDatabase
import com.tkdev.muzapp.domain.models.ChatItemAsset
import com.tkdev.muzapp.domain.models.ChatItemDomain
import com.tkdev.muzapp.domain.models.UserDomain
import com.tkdev.muzapp.domain.models.UserAsset
import kotlinx.coroutines.coroutineScope

class PreBuildWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = AppDatabase.getDatabase(context)

            applicationContext.assets.open(USERS_FILE).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val userType = object : TypeToken<List<UserAsset>>() {}.type
                    val users: List<UserAsset> = Gson().fromJson(jsonReader, userType)

                    database.usersDao().insertAllUsers(users.map { userAsset ->
                        UserDomain(userAsset.userId, userAsset.userName)
                    })
                }
            }

            applicationContext.assets.open(MESSAGES_FILE).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val itemType = object : TypeToken<List<ChatItemAsset>>() {}.type
                    val items: List<ChatItemAsset> = Gson().fromJson(jsonReader, itemType)

                    database.chatDao().prepopulateData(items.map { itemAsset ->
                        ChatItemDomain(
                            itemAsset.messageId,
                            itemAsset.chatId,
                            itemAsset.sendByUserId,
                            itemAsset.messageTimestamp,
                            itemAsset.chatMessage,
                            itemAsset.wasItRead
                        )
                    })

                }
            }
            Log.d("TAG", "doWork: ")

            Result.success()

        } catch (ex: Exception) {
            Log.d("TAG", "doWork: ${ex.localizedMessage}")
            Result.failure()
        }
    }
}