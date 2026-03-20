package com.example.kmptraining.kmp_session4.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kmptraining.kmp_session4.data.local.entity.PublicRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PublicRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublicRepo(publicRepoEntity: PublicRepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublicRepos(publicRepoEntities: List<PublicRepoEntity>)

    @Query("SELECT * FROM public_repos")
    fun getPublicRepos(): Flow<List<PublicRepoEntity>>

    @Query("SELECT * FROM public_repos WHERE id = :id")
    fun getPublicRepoById(id: Int): Flow<PublicRepoEntity?>

    @Query("DELETE FROM public_repos WHERE id = :id")
    suspend fun deletePublicRepoById(id: Int)

    @Query("DELETE FROM public_repos")
    suspend fun deleteAllPublicRepos()
}