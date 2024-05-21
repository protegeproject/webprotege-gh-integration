package edu.stanford.protege.github;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * A summary representation of a GitHub repository
 */
public record GitHubRepository(@JsonProperty("id") long id,
                               @JsonProperty("node_id") String nodeId,
                               @JsonProperty("name") String name,
                               @JsonProperty("full_name") String fullName,
                               @JsonProperty("description") String description) implements GitHubObject {

    public GitHubRepository(@JsonProperty("id") long id,
                            @JsonProperty("node_id") String nodeId,
                            @JsonProperty("name") String name,
                            @JsonProperty("full_name") String fullName,
                            @JsonProperty("description") String description) {
        this.id = id;
        this.nodeId = Objects.requireNonNullElse(nodeId, "");
        this.name = Objects.requireNonNullElse(name, "");
        this.fullName = Objects.requireNonNullElse(fullName, "");
        this.description = Objects.requireNonNullElse(description, "");
    }

    @JsonCreator
    public static GitHubRepository get(@JsonProperty("id") long id,
                                       @JsonProperty("node_id") String nodeId,
                                       @JsonProperty("name") String name,
                                       @JsonProperty("full_name") String fullName,
                                       @JsonProperty("description") String description) {
        return new GitHubRepository(id, nodeId, name, fullName, description);
    }

    @JsonIgnore
    public GitHubRepositoryCoordinates getCoordinates() {
        return GitHubRepositoryCoordinates.fromFullName(fullName());
    }
}
