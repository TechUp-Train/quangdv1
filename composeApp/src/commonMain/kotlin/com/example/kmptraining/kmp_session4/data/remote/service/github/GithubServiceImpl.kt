package com.example.kmptraining.kmp_session4.data.remote.service.github

import com.example.kmptraining.kmp_session4.data.remote.dto.PublicRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.UserRepoDto
import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GithubServiceImpl(
    private val client: HttpClient
) : GithubService {
    override suspend fun getUser(): UserDto {
        return client.get("user").body()
    }

    override suspend fun getPublicRepos(): List<PublicRepoDto> {
        return client.get("repositories").body()
    }

    override suspend fun getUserRepos(): List<UserRepoDto> {
        return client.get("user/repos").body()
    }

    override suspend fun searchRepos(
        query: String,
        sort: String?,
        order: String?,
        perPage: Int,
        page: Int
    ): SearchResultDto {
        return client.get("search/repositories") {
            url {
                parameters.append("q", query)
                sort?.let { parameters.append("sort", it) }
                order?.let { parameters.append("order", it) }
                parameters.append("per_page", perPage.toString())
                parameters.append("page", page.toString())
            }
        }.body()
    }
}
