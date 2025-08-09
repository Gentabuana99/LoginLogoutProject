package com.example.loginlogoutproject

import android.R
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Nama  : Genta Buana
//NIM   : 10119369
//Kelas : AKBUL-1

private val Context.dataStore by preferencesDataStore(name="user_preferences")

class DataStoreManager(private val context: Context) {

    private object PreferenesKeys {
        val NAMA = stringPreferencesKey("nama")
        val NO_HP = stringPreferencesKey("no_hp")
        val EMAIL = stringPreferencesKey("email")
    }

    suspend fun saveUserData(nama: String, email: String, nohp : String){
        context.dataStore.edit{ prefs ->
            prefs[PreferenesKeys.NAMA] = nama
            prefs[PreferenesKeys.EMAIL] = email
            prefs[PreferenesKeys.NO_HP] = nohp
        }
    }

    suspend fun clearUserData(){
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }


    val userDataFlow: Flow<UserData?> = context.dataStore.data.map {prefs ->
        val nama = prefs[PreferenesKeys.NAMA]
        val email = prefs[PreferenesKeys.EMAIL]
        val nohp = prefs[PreferenesKeys.NO_HP]

        if ( nama!=null && email!=null && nohp!=null){
            UserData(nama,email,nohp)
        }else {
            null
        }
    }
}