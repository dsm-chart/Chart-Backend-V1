package com.chart.chart.infra.github.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import java.util.*


@NoArgsConstructor
@AllArgsConstructor
@Builder
data class GithubUserInfoResponse (
    @JsonProperty("login")
    var login: String,
    @JsonProperty("id")
    var id: Int ,
    @JsonProperty("node_id")
    var nodeId: String,
    @JsonProperty("avatar_url")
    var avatarUrl: String,
    @JsonProperty("gravatar_id")
    var gravatarId: String,
    @JsonProperty("url")
    var url: String,
    @JsonProperty("html_url")
    var htmlUrl: String,
    @JsonProperty("followers_url")
    var followersUrl: String,
    @JsonProperty("following_url")
    var followingUrl: String,
    @JsonProperty("gists_url")
    var gistsUrl: String,
    @JsonProperty("starred_url")
    var starredUrl: String,
    @JsonProperty("subscriptions_url")
    var subscriptionsUrl: String,
    @JsonProperty("organizations_url")
    var organizationsUrl: String,
    @JsonProperty("repos_url")
    var reposUrl: String,
    @JsonProperty("events_url")
    var eventsUrl: String,
    @JsonProperty("received_events_url")
    var receivedEventsUrl: String,
    @JsonProperty("type")
    var type: String,
    @JsonProperty("site_admin")
    var siteAdmin: Boolean,
    @JsonProperty("name")
    var name: String,
    @JsonProperty("company")
    var company: String?,
    @JsonProperty("blog")
    var blog: String?,
    @JsonProperty("location")
    var location: String?,
    @JsonProperty("email")
    var email: String?,
    @JsonProperty("hireable")
    var hireable: String?,
    @JsonProperty("bio")
    var bio: String?,
    @JsonProperty("twitter_username")
    var twitterUsername: String?,
    @JsonProperty("public_repos")
    var publicRepos: Int,
    @JsonProperty("public_gists")
    var publicGists: Int,
    @JsonProperty("followers")
    var followers: Int,
    @JsonProperty("following")
    var following: Int,
    @JsonProperty("created_at")
    var createdAt: Date,
    @JsonProperty("updated_at")
    var updatedAt: Date
)
