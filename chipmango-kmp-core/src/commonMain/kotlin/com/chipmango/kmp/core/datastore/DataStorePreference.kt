package com.chipmango.kmp.core.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreference(private val dataStoreFactory: DataStoreFactory) {
    private val dataStore by lazy { dataStoreFactory.createPlatformDataStore() }

    suspend fun save(key: String, value: Boolean) {
        dataStore.edit { settings ->
            val prefKey = booleanPreferencesKey(key)
            settings[prefKey] = value
        }
    }

    suspend fun save(key: String, value: Int) {
        dataStore.edit { settings ->
            val prefKey = intPreferencesKey(key)
            settings[prefKey] = value
        }
    }

    suspend fun save(key: String, value: Float) {
        dataStore.edit { settings ->
            val prefKey = floatPreferencesKey(key)
            settings[prefKey] = value
        }
    }

    suspend fun save(key: String, value: Long) {
        dataStore.edit { settings ->
            val prefKey = longPreferencesKey(key)
            settings[prefKey] = value
        }
    }

    suspend fun save(key: String, value: String) {
        dataStore.edit { settings ->
            val prefKey = stringPreferencesKey(key)
            settings[prefKey] = value
        }
    }

    suspend fun save(key: String, value: Set<String>) {
        dataStore.edit { settings ->
            val prefKey = stringSetPreferencesKey(key)
            settings[prefKey] = value
        }
    }

    fun read(key: String, defaultValue: Boolean): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: defaultValue }
    }

    fun read(key: String, defaultValue: Int): Flow<Int> {
        val prefKey = intPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: defaultValue }
    }

    fun read(key: String, defaultValue: Float): Flow<Float> {
        val prefKey = floatPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: defaultValue }
    }

    fun read(key: String, defaultValue: Long): Flow<Long> {
        val prefKey = longPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: defaultValue }
    }

    fun read(key: String, defaultValue: String): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: defaultValue }
    }

    fun read(key: String, defaultValue: Set<String>): Flow<Set<String>> {
        val prefKey = stringSetPreferencesKey(key)
        return dataStore.data.map { it[prefKey] ?: defaultValue }
    }
}