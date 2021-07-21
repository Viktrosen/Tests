package com.geekbrains.tests.repository

import com.geekbrains.tests.model.api.entity.SearchResponse
import retrofit2.Response

interface RepositoryCallback {
    fun handleGitHubResponse(response: Response<SearchResponse?>?)
    fun handleGitHubError()
}
