package com.example.clothingsuggester.data

interface SharedPreferencesService {
    fun saveToken(value: String)
    fun getToken(): String?
}