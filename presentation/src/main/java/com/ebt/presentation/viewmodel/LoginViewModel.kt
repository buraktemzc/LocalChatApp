package com.ebt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.common.utils.network.Result
import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.usecase.message.GetMessagesFromRemoteUseCase
import com.ebt.domain.usecase.message.SaveMessagesUseCase
import com.ebt.domain.usecase.session.GetLoggedInUserUseCase
import com.ebt.domain.usecase.session.SaveLoggedInUserUseCase
import com.ebt.domain.usecase.user.SaveUserUseCase
import com.ebt.domain.usecase.user.SaveUsersFromMessagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveLoggedInUserUseCase: SaveLoggedInUserUseCase,
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase,
    private val getMessagesFromRemoteUseCase: GetMessagesFromRemoteUseCase,
    private val saveMessagesUseCase: SaveMessagesUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val saveUsersFromMessagesUseCase: SaveUsersFromMessagesUseCase,
    private val loggedInUserUseCase: SaveLoggedInUserUseCase,
) : ViewModel() {

    private val _sessionSaved = MutableLiveData(false)
    val sessionSaved: LiveData<Boolean> = _sessionSaved

    private val _sessionExist = MutableLiveData(false)
    val sessionExist: LiveData<Boolean> = _sessionExist

    private val _dbSynced = MutableLiveData(false)
    val dbSynced: LiveData<Boolean> = _dbSynced

    private val _nicknameValidationCorrect = MutableLiveData<Boolean>()
    val nicknameValidationCorrect: LiveData<Boolean> = _nicknameValidationCorrect

    private val _messages = MutableLiveData<Result<List<MessageEntity>>>()
    val messages: LiveData<Result<List<MessageEntity>>> = _messages

    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String> = _userNickname

    init {
        checkSessionExist()
    }

    fun saveCurrentSession(nickname: String) = viewModelScope.launch {
        saveUserUseCase.execute(SaveUserUseCase.Params(nickname))
        saveLoggedInUserUseCase.execute(SaveLoggedInUserUseCase.Params(nickname))
        _userNickname.value = nickname
        _sessionSaved.value = true
    }

    fun checkNicknameValidationCorrect(nickname: String) {
        _nicknameValidationCorrect.value = nickname.length > 2
    }

    fun getMessagesFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        _messages.postValue(Result.Loading())
        try {
            val result = getMessagesFromRemoteUseCase.execute(Unit)
            _messages.postValue(result)
        } catch (e: Exception) {
            _messages.postValue(Result.Error(e.message.toString()))
        }
    }

    fun saveMessages(messageList: List<MessageEntity>) = viewModelScope.launch {
        saveUsersFromMessagesUseCase.execute(SaveUsersFromMessagesUseCase.Params(messageList))
        saveMessagesUseCase.execute(SaveMessagesUseCase.Params(messageList))
        _dbSynced.value = true
    }

    fun clearUserSession() = viewModelScope.launch {
        loggedInUserUseCase.execute(SaveLoggedInUserUseCase.Params(""))
    }

    private fun checkSessionExist() = viewModelScope.launch {
        val nickname = getLoggedInUserUseCase.execute(Unit)
        _userNickname.value = nickname
        _sessionExist.value = nickname.isNotEmpty()
    }
}