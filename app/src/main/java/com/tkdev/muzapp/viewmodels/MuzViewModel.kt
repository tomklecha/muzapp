package com.tkdev.muzapp.viewmodels

import androidx.lifecycle.*
import com.tkdev.muzapp.common.CoroutineContextProvider
import com.tkdev.muzapp.common.SingleEvent
import com.tkdev.muzapp.domain.models.ChatItemDomain
import com.tkdev.muzapp.domain.models.UserDomain
import com.tkdev.muzapp.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MuzViewModel @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val chatRepository: ChatRepository
) : ViewModel() {

    val currentUserDomain: LiveData<UserDomain> = chatRepository.fetchCurrentUser().asLiveData()
    val secondUserDomain: LiveData<UserDomain> = chatRepository.fetchSecondUser().asLiveData()

    lateinit var chatItems: LiveData<List<ChatItemDomain>>

    private val _snackbarMessage: MutableLiveData<SingleEvent<String>> = MutableLiveData()
    val snackbarMessage: LiveData<SingleEvent<String>> = _snackbarMessage

    private val _editTextValue: MutableLiveData<SingleEvent<String>> = MutableLiveData()
    val editTextValue: LiveData<SingleEvent<String>> = _editTextValue

    fun sendChatMessage(message: String, chatId: String) {
        viewModelScope.launch(coroutineContextProvider.Io) {
            chatRepository.sendMessageToUser(
                message, chatId
            )
            _editTextValue.postValue(SingleEvent(""))
        }
    }

    fun fetchChatMessages(chatId: String) {
        viewModelScope.launch {
            chatItems = chatRepository.fetchChatMessages(chatId).asLiveData()
        }
    }

    fun prepopulateData() {
        viewModelScope.launch(coroutineContextProvider.Io) {
            chatRepository.prepopulateData()
            _snackbarMessage.postValue(SingleEvent("Data from mockup generated successfully"))
        }
    }

    fun insertMockMessage(chatId: String) {
        viewModelScope.launch(coroutineContextProvider.Io) {
            chatRepository.sendMessageAsRecipient(chatId)
            _snackbarMessage.postValue(SingleEvent("New message generated successfully"))
        }
    }

    fun showSnackbar(message: String) {
        _snackbarMessage.postValue(SingleEvent(message))
    }

    fun clearChat() {
        viewModelScope.launch(coroutineContextProvider.Io) {
            chatRepository.clearChat()
            _snackbarMessage.postValue(SingleEvent("Data cleared successfully"))
        }
    }
}