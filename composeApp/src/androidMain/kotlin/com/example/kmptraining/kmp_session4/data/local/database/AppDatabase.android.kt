package com.example.kmptraining.kmp_session4.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = context.getDatabasePath("app_database.db")
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        dbFile.absolutePath
    )
}