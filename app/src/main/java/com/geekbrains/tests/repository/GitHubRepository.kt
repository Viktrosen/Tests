package com.geekbrains.tests.repository

import com.geekbrains.tests.model.api.GitHubApi
import com.geekbrains.tests.model.api.entity.SearchResult
import com.geekbrains.tests.model.api.entity.SearchUserResponse

internal class GitHubRepository(private val gitHubApi: GitHubApi) : RepositoryContract {

    override suspend fun searchGithubAsync(query: String): SearchUserResponse {
        return gitHubApi.searchGithubAsync(query).await()
    }

    override suspend fun searchGithubUser(query: String): List<SearchResult> {
        return gitHubApi.searchGithubUsersAsync(query).await()
    }
}
