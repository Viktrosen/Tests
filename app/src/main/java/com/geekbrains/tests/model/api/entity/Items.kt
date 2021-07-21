package com.geekbrains.tests.model.api.entity

import com.google.gson.annotations.SerializedName


data class Items(

    @SerializedName("login") var login: String,
    @SerializedName("id") var id: Int,
    @SerializedName("node_id") var nodeId: String,
    @SerializedName("avatar_url") var avatarUrl: String,
    @SerializedName("gravatar_id") var gravatarId: String,
    @SerializedName("url") var url: String,
    @SerializedName("html_url") var htmlUrl: String,
    @SerializedName("followers_url") var followersUrl: String,
    @SerializedName("subscriptions_url") var subscriptionsUrl: String,
    @SerializedName("organizations_url") var organizationsUrl: String,
    @SerializedName("repos_url") var reposUrl: String,
    @SerializedName("received_events_url") var receivedEventsUrl: String,
    @SerializedName("type") var type: String,
    @SerializedName("score") var score: Int,
    @SerializedName("following_url") var followingUrl: String,
    @SerializedName("gists_url") var gistsUrl: String,
    @SerializedName("starred_url") var starredUrl: String,
    @SerializedName("events_url") var eventsUrl: String,
    @SerializedName("site_admin") var siteAdmin: Boolean

)