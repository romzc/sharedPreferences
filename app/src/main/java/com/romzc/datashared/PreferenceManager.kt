package com.romzc.datashared

import android.content.Context
import android.content.SharedPreferences


class PreferenceManager(context: Context) {

    private val PREFERENCE = "MY_PREFERENCE"
    private val SHA_NAME = "USER_NAME"


    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE)

    fun getUser(): String {
        return sharedPreferences.getString(SHA_NAME, "") ?: ""
    }

    fun putUser(value: String) {
        return sharedPreferences.edit().putString(SHA_NAME, value).apply()
    }

}