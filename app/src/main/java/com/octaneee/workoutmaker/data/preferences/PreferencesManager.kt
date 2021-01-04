package com.octaneee.workoutmaker.data.preferences

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import com.octaneee.workoutmaker.other.Constant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.createDataStore(Constant.DATA_STORE_NAME)

    private object PreferencesKeys {
        val FIRST_TIME = preferencesKey<Boolean>("first_time")
        val USER_NAME = preferencesKey<String>("user_name")
        val USER_HEIGHT = preferencesKey<Int>("user_height")
        val CURRENT_MACROCYCLE_ID = preferencesKey<Long>("current_macrocycle_id")
    }

    val firstTime = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val isFirstTime = preferences[PreferencesKeys.FIRST_TIME] ?: true
            isFirstTime
        }

    suspend fun saveFirstTime(firstTime: Boolean) =
        dataStore.edit { it[PreferencesKeys.FIRST_TIME] = firstTime }

    val userName = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val name = preferences[PreferencesKeys.USER_NAME] ?: "User"
            name
        }

    suspend fun saveUserName(userName: String) =
        dataStore.edit { it[PreferencesKeys.USER_NAME] = userName }

    val userHeight = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val height = preferences[PreferencesKeys.USER_HEIGHT] ?: 180
            height
        }

    suspend fun saveUserHeight(userHeight: Int) =
        dataStore.edit { it[PreferencesKeys.USER_HEIGHT] = userHeight }

    val currentMacrocycleId = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val id = preferences[PreferencesKeys.CURRENT_MACROCYCLE_ID]
            id
        }

    suspend fun saveCurrentMacrocycleId(currentMacrocycleId: Long) =
        dataStore.edit { it[PreferencesKeys.CURRENT_MACROCYCLE_ID] = currentMacrocycleId }

}