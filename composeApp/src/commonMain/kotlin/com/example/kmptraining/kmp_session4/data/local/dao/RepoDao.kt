package com.example.kmptraining.kmp_session4.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kmptraining.kmp_session4.data.local.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repoEntity: RepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repoEntities: List<RepoEntity>)

    @Query("SELECT * FROM repos")
    fun getAllRepos(): Flow<List<RepoEntity>>

    @Query("SELECT * FROM repos WHERE repoType = :type")
    fun getReposByType(type: String): Flow<List<RepoEntity>>

    @Query("SELECT * FROM repos WHERE id = :id")
    fun getRepoById(id: Int): Flow<RepoEntity?>

    @Query("DELETE FROM repos WHERE repoType = :type")
    suspend fun deleteReposByType(type: String)

    @Query("DELETE FROM repos")
    suspend fun deleteAllRepos()
}
