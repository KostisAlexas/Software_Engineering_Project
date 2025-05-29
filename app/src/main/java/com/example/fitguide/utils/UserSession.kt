package com.example.fitguide.utils

import android.content.Context
import com.example.fitguide.domain.model.User

object UserSession {
    private const val PREFS_NAME = "user_session"
    private const val KEY_USER_ID = "user_id"
    private var cachedUserId: Long? = null

    fun isLoggedIn(context: Context): Boolean {
        if (cachedUserId != null) return true
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val id = prefs.getLong(KEY_USER_ID, -1L)
        return if (id != -1L) {
            cachedUserId = id
            true
        } else {
            false
        }
    }

    fun login(context: Context, user: User) {
        val id = user.id
        if (id != null) {
            cachedUserId = id
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putLong(KEY_USER_ID, id).apply()
        }
    }

    fun logout(context: Context) {
        cachedUserId = null
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_USER_ID).apply()
    }

    fun getCurrentUserId(context: Context): Long? {
        if (cachedUserId != null) return cachedUserId
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val id = prefs.getLong(KEY_USER_ID, -1L)
        return if (id != -1L) {
            cachedUserId = id
            id
        } else {
            null
        }
    }
}
