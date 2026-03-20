package com.example.kmptraining.kmp_session4.data.local.database

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.example.kmptraining.kmp_session4.data.local.Converters
import com.example.kmptraining.kmp_session4.data.local.dao.PublicRepoDao
import com.example.kmptraining.kmp_session4.data.local.dao.UserDao
import com.example.kmptraining.kmp_session4.data.local.dao.UserRepoDao
import com.example.kmptraining.kmp_session4.data.local.entity.PublicRepoEntity
import com.example.kmptraining.kmp_session4.data.local.entity.UserEntity
import com.example.kmptraining.kmp_session4.data.local.entity.UserRepoEntity

@Database(
    entities = [UserEntity::class, UserRepoEntity::class, PublicRepoEntity::class], version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
    ],
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userRepoDao(): UserRepoDao
    abstract fun publicRepoDao(): PublicRepoDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}