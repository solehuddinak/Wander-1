package dev.dizzy1021.core.utils

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class SharedPreferenceUtil @Inject constructor(
    private val pref: SharedPreferences
) {

    fun getStatusOnboard(): Boolean {
        return pref.getBoolean(PREF_ONBOARD, false)
    }

    fun setStatusOnboard(value: Boolean) {
        val editor: SharedPreferences.Editor = pref.edit()

        editor.putBoolean(PREF_ONBOARD, value)
        editor.apply()
    }

    fun getUser(): String? {
        return pref.getString(PREF_USER, null)
    }

    fun setUser() {
        val editor: SharedPreferences.Editor = pref.edit()
        val user = UUID.randomUUID().toString()

        editor.putString(PREF_USER, user)
        editor.apply()
    }
}