package com.example.clothingsuggester.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.clothingsuggester.utils.Constants.APIKEY
import com.example.clothingsuggester.utils.Constants.PREFS_NAME

class SharedPreferencesServiceImpl(
    var sharedPreferences: SharedPreferences,
    context: Context
) :
    SharedPreferencesService {

    init {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )


    }

    override fun saveToken(value: String) {
        sharedPreferences.edit().putString(APIKEY, value).apply()
    }

    override fun getToken(): String? {
      return  sharedPreferences.getString(APIKEY, "")
    }

}