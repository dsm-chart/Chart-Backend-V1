package com.chart.chart.infra.github.data.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class RepoGithubUserInfoResponse (
    @JsonProperty("login")
    val login: String,

    @JsonProperty("id")
    val id: Long,

    @JsonProperty("node_id")
    val nodeID: String,

    @JsonProperty("avatar_url")
    val avatarURL: String,

    @JsonProperty("gravatar_id")
    val gravatarID: String,

    @JsonProperty("url")
    val url: String,

    @JsonProperty("html_url")
    val htmlURL: String,

    @JsonProperty("followers_url")
    val followersURL: String,

    @JsonProperty("following_url")
    val followingURL: String,

    @JsonProperty("gists_url")
    val gistsURL: String,

    @JsonProperty("starred_url")
    val starredURL: String,

    @JsonProperty("subscriptions_url")
    val subscriptionsURL: String,

    @JsonProperty("organizations_url")
    val organizationsURL: String,

    @JsonProperty("repos_url")
    val reposURL: String,

    @JsonProperty("events_url")
    val eventsURL: String,

    @JsonProperty("received_events_url")
    val receivedEventsURL: String,

    @JsonProperty("type")
    val type: String,

    @JsonProperty("site_admin")
    val siteAdmin: Boolean,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("company")
    val company: Any? = null,

    @JsonProperty("blog")
    val blog: String,

    @JsonProperty("location")
    val location: Any? = null,

    @JsonProperty("email")
    val email: Any? = null,

    @JsonProperty("hireable")
    val hireable: Any? = null,

    @JsonProperty("bio")
    val bio: String,

    @JsonProperty("twitter_username")
    val twitterUsername: Any? = null,

    @JsonProperty("public_repos")
    val publicRepos: Long,

    @JsonProperty("public_gists")
    val publicGists: Long,

    @JsonProperty("followers")
    val followers: Long,

    @JsonProperty("following")
    val following: Long,

    @JsonProperty("created_at")
    val createdAt: String,

    @JsonProperty("updated_at")
    val updatedAt: String
)

