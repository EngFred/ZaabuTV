package com.engineerfred.zaabutv.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "zaabutv_user_prefs")

@Singleton
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val KEY_COMPLETED_ONBOARDING = booleanPreferencesKey("completed_onboarding")
        private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val KEY_USER_NAME = stringPreferencesKey("user_name")
        private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
        private val KEY_IS_SUBSCRIBED = booleanPreferencesKey("is_subscribed")
        private val KEY_ACTIVE_PLAN_ID = stringPreferencesKey("active_plan_id")
    }

    val hasCompletedOnboarding: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_COMPLETED_ONBOARDING] ?: false
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_IS_LOGGED_IN] ?: false
    }

    val userName: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_USER_NAME]
    }

    val userEmail: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_USER_EMAIL]
    }

    val isSubscribed: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_IS_SUBSCRIBED] ?: false
    }

    val activePlanId: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[KEY_ACTIVE_PLAN_ID]
    }

    suspend fun setCompletedOnboarding(completed: Boolean = true) {
        context.dataStore.edit { prefs ->
            prefs[KEY_COMPLETED_ONBOARDING] = completed
        }
    }

    suspend fun saveUserData(name: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_LOGGED_IN] = true
            prefs[KEY_USER_NAME] = name
            prefs[KEY_USER_EMAIL] = email
        }
    }

    suspend fun setSubscriptionActive(planId: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_SUBSCRIBED] = true
            prefs[KEY_ACTIVE_PLAN_ID] = planId
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_LOGGED_IN] = false
            prefs.remove(KEY_USER_NAME)
            prefs.remove(KEY_USER_EMAIL)
        }
    }
}
