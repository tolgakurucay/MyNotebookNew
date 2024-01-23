package com.tolgakurucay.mynotebooknew.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val DATASTORE_NAME = "myNotebookDataStore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreManager @Inject constructor(val context: Context){

    private val myNotebookDataStore: DataStore<Preferences> = context.dataStore

    suspend fun storeLanguageTag(tag: AppLanguage){
        myNotebookDataStore.edit {pref->
            pref[LANGUAGE_TAG] = tag.name.lowercase()
        }
    }

    fun getLanguageTag(): Flow<String> =
        myNotebookDataStore.data.map {pref->
            pref[LANGUAGE_TAG] ?: AppLanguage.TR.name.lowercase()
        }

    suspend fun storeIsDarkModeTag(isDarkMode: Boolean){
        myNotebookDataStore.edit { pref-> pref[IS_DARK_MODE_TAG] = isDarkMode }
    }

    fun getIsDarkModeTag(): Flow<Boolean?> = myNotebookDataStore.data.map { pref-> pref[IS_DARK_MODE_TAG]  }


    companion object{
        val LANGUAGE_TAG = stringPreferencesKey("LANGUAGE_TAG")
        val IS_DARK_MODE_TAG = booleanPreferencesKey("IS_DARK_MODE_TAG")
    }

}