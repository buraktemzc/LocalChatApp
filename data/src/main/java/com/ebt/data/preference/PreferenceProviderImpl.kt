package com.ebt.data.preference

import android.content.Context
import android.content.SharedPreferences

class PreferenceProviderImpl(context: Context) : PreferenceProvider {
    companion object {
        private const val KEY_PREF_NAME = "chat-pref"
    }

    private var preference: SharedPreferences =
        context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE)

    override fun hasValue(key: String): Boolean {
        return preference.contains(key)
    }

    override fun getString(key: String, defValue: String): String {
        return preference.getString(key, defValue) ?: defValue
    }

    override fun putString(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }
}