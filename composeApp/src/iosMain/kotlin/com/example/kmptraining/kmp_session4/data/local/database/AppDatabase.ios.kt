package com.example.kmptraining.kmp_session4.data.local.database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val fileManager = NSFileManager.defaultManager
    val urls = fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)
    val documentDir = urls.first() as NSURL
    val dbPath = documentDir.path + "/app_database.db"

    return Room.databaseBuilder<AppDatabase>(
        name = dbPath
    )
}