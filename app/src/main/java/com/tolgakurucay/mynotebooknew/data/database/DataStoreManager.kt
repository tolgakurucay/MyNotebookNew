package com.tolgakurucay.mynotebooknew.data.database

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private const val DATASTORE_NAME = "myNotebookDataStore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreManager(val context: Context){

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


    companion object{
        val LANGUAGE_TAG = stringPreferencesKey("LANGUAGE_TAG")
    }

}