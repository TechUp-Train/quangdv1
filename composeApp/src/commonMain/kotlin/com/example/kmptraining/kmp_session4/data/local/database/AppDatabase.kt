package com.example.kmptraining.kmp_session4.data.local.database

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.DeleteTable
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.example.kmptraining.kmp_session4.data.local.Converters
import com.example.kmptraining.kmp_session4.data.local.dao.RepoDao
import com.example.kmptraining.kmp_session4.data.local.dao.UserDao
import com.example.kmptraining.kmp_session4.data.local.entity.RepoEntity
import com.example.kmptraining.kmp_session4.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, RepoEntity::class], version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4, spec = AppDatabase.Migration3To4::class),
    ],
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repoDao(): RepoDao

    @RenameTable(fromTableName = "user_repos", toTableName = "repos")
    @DeleteTable(tableName = "public_repos")
    class Migration3To4 : AutoMigrationSpec
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}