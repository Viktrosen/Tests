package com.geekbrains.tests.repository

import com.geekbrains.tests.model.api.entity.SearchResponse
import com.geekbrains.tests.model.api.entity.SearchResult
import com.geekbrains.tests.model.api.entity.SearchUserResponse
import com.geekbrains.tests.repository.RepositoryCallback
import io.reactivex.Observable

interface RepositoryContract {

    suspend fun searchGithubAsync(
        query: String
    ): SearchUserResponse

    suspend fun searchGithubUser(query: String): List<SearchResult>
}
