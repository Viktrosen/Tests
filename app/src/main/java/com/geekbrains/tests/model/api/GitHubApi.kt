package com.geekbrains.tests.model.api

import com.geekbrains.tests.model.api.entity.SearchResponse
import com.geekbrains.tests.model.api.entity.SearchResult
import com.geekbrains.tests.model.api.entity.SearchUserResponse
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GitHubApi {

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/users")
    fun searchGithubAsync(@Query("q") term: String?): Deferred<SearchUserResponse>

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("users/{q}/repos")
    fun searchGithubUsersAsync(@Path("q") term: String?): Deferred<List<SearchResult>>
}
