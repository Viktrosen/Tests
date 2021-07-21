package com.geekbrains.tests.model.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("owner")
    @Expose
    val owner: Owner,
    @SerializedName("full_name")
    @Expose
    val fullName: String?,
    @SerializedName("private")
    @Expose val private: Boolean?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String?,
    @SerializedName("size")
    @Expose
    val size: Int?,
    @SerializedName("stargazers_count")
    @Expose
    val stargazersCount: Int?,
    @SerializedName("language")
    @Expose
    val language: String?,
    @SerializedName("has_wiki")
    @Expose
    val hasWiki: Boolean?,
    @SerializedName("archived")
    @Expose
    val archived: Boolean?,
    @SerializedName("score")
    @Expose
    val score: Double?,
    @SerializedName("repos_url")
    @Expose
    val reposUrl: String,
    @SerializedName("archive_url")
    @Expose
    val archiveUrl: String
)
