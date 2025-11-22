package com.slyfly.repas.core.datastore


import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("session_prefs")

class SessionManager(private val context: Context) {

    private object Keys {
        val TOKEN: Preferences.Key<String> = stringPreferencesKey("auth_token")
    }

    // Flow pour lire en réactif
    val tokenFlow: Flow<String?> = context.dataStore.data.map { it[Keys.TOKEN] }

    // Lire une fois (suspend)
    suspend fun getTokenOnce(): String? = tokenFlow.first()

    // Écrire (suspend)
    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs -> prefs[Keys.TOKEN] = token }
    }

    suspend fun clear() {
        context.dataStore.edit { prefs -> prefs.remove(Keys.TOKEN) }
    }
}
