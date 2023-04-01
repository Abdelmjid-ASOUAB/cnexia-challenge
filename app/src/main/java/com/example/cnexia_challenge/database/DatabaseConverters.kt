package com.example.cnexia_challenge.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverters {
    @TypeConverter
    fun stringToListString(value: String?): List<String?>? {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listStringToString(list: List<String?>?): String? {
        return Gson().toJson(list)
    }
}
