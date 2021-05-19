package com.ebt.data.session

import com.ebt.data.preference.PreferenceProvider

class LoggedInUser(private val preferenceProvider: PreferenceProvider) {

    companion object {
        private const val KEY_USER_NICKNAME = "user-nickname"
    }

    var userNickname: String = preferenceProvider.getString(KEY_USER_NICKNAME)
        set(value) {
            preferenceProvider.putString(KEY_USER_NICKNAME, value)
        }
}
