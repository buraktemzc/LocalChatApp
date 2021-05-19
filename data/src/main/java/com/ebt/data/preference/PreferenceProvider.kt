package com.ebt.data.preference

interface PreferenceProvider {
    fun hasValue(key: String): Boolean

    fun getString(key: String, defValue: String = ""): String
    fun putString(key: String, value: String)
}