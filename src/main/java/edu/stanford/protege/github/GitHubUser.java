package edu.stanford.protege.github;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-07-12
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubUser(@JsonProperty("login") @Nullable String login,
                         @JsonProperty("id") long id,
                         @JsonProperty("node_id") @Nullable String nodeId,
                         @JsonProperty("avatar_url") @Nullable String avatarUrl,
                         @JsonProperty("url") @Nullable String url,
                         @JsonProperty("html_url") @Nullable String htmlUrl,
                         @JsonProperty("type") @Nullable GitHubUserType type,
                         @JsonProperty("site_admin") boolean siteAdmin) {

    public GitHubUser(@JsonProperty("login") @Nullable String login,
                      @JsonProperty("id") long id,
                      @JsonProperty("node_id") @Nullable String nodeId,
                      @JsonProperty("avatar_url") @Nullable String avatarUrl,
                      @JsonProperty("url") @Nullable String url,
                      @JsonProperty("html_url") @Nullable String htmlUrl,
                      @JsonProperty("type") @Nullable GitHubUserType type,
                      @JsonProperty("site_admin") boolean siteAdmin) {
        this.id = id;
        this.login = Objects.requireNonNullElse(login, "");
        this.nodeId = Objects.requireNonNullElse(nodeId, "");
        this.avatarUrl = Objects.requireNonNullElse(avatarUrl, "");
        this.url = Objects.requireNonNullElse(url, "");
        this.htmlUrl = Objects.requireNonNullElse(htmlUrl, "");
        this.type = Objects.requireNonNullElse(type, GitHubUserType.USER);
        this.siteAdmin = siteAdmin;
    }

    @JsonCreator
    public static GitHubUser get(@JsonProperty("login") @Nullable String login,
                                 @JsonProperty("id") long id,
                                 @JsonProperty("node_id") @Nullable String nodeId,
                                 @JsonProperty("avatar_url") @Nullable String avatarUrl,
                                 @JsonProperty("url") @Nullable String url,
                                 @JsonProperty("html_url") @Nullable String htmlUrl,
                                 @JsonProperty("type") @Nullable GitHubUserType type,
                                 @JsonProperty("site_admin") boolean siteAdmin) {
        return new GitHubUser(login, id, nodeId, avatarUrl, url, htmlUrl, type, siteAdmin);
    }

    public static GitHubUser empty() {
        return get("", 0, "", "", "", "", GitHubUserType.USER, false);
    }
}

