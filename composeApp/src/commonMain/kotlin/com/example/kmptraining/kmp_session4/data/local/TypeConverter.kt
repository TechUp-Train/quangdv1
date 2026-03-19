package com.example.kmptraining.kmp_session4.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromTopics(topics: List<String>?): String? {
        return topics?.joinToString(",")
    }

    @TypeConverter
    fun toTopics(data: String?): List<String>? {
        return data?.split(",")
    }
}