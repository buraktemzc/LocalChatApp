package com.ebt.presentation.viewmodel

import androidx.lifecycle.*
import com.ebt.domain.entity.UserEntity
import com.ebt.domain.usecase.message.GetMessagesFromDBUseCase
import com.ebt.domain.usecase.message.SaveMessageUseCase
import com.ebt.domain.usecase.session.SaveLoggedInUserUseCase
import com.ebt.domain.usecase.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val loggedInUserUseCase: SaveLoggedInUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getMessagesFromDBUseCase: GetMessagesFromDBUseCase,
    private val saveMessageUseCase: SaveMessageUseCase
) : ViewModel() {

    private val _sessionCleared = MutableLiveData(false)
    val sessionCleared: LiveData<Boolean> = _sessionCleared

    private val _activeUser = MutableLiveData<UserEntity?>()
    val activeUser: LiveData<UserEntity?> = _activeUser

    private val _newMessageSent = MutableLiveData<Boolean>()
    val newMessageSent: LiveData<Boolean> = _newMessageSent

    fun setActiveUser(nickname: String) = viewModelScope.launch {
        val user = getUserUseCase.execute(GetUserUseCase.Params(nickname))
        user?.let {
            _activeUser.value = user
        }
    }

    fun clearUserSession() = viewModelScope.launch {
        loggedInUserUseCase.execute(SaveLoggedInUserUseCase.Params(""))
        _sessionCleared.value = true
    }

    fun getAllMessagesFromDB() = liveData {
        getMessagesFromDBUseCase.execute(Unit).collect {
            emit(it)
        }
    }

    fun saveMessage(message: String) = viewModelScope.launch {
        if (message.isNotEmpty() && activeUser.value != null) {
            val params = SaveMessageUseCase.Params(message, activeUser.value!!)
            val inserted = saveMessageUseCase.execute(params)
            _newMessageSent.value = true
        }
    }
}