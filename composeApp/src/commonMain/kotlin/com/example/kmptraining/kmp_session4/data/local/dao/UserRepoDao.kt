package com.example.kmptraining.kmp_session4.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kmptraining.kmp_session4.data.local.entity.UserRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRepo(userRepoEntity: UserRepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRepos(userRepoEntities: List<UserRepoEntity>)

    @Query("SELECT * FROM user_repos")
    fun getUserRepos(): Flow<List<UserRepoEntity>>

    @Query("SELECT * FROM user_repos WHERE id = :id")
    fun getUserRepoById(id: Int): Flow<UserRepoEntity?>

    @Query("DELETE FROM user_repos WHERE id = :id")
    suspend fun deleteUserRepoById(id: Int)

    @Query("DELETE FROM user_repos")
    suspend fun deleteAllUserRepos()
}