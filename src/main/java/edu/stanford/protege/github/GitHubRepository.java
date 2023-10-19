package edu.stanford.protege.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * A summary representation of a GitHub repository
 * @param id The repository identifier
 * @param nodeId The unique GraphQL node identifier
 * @param name The name of the repository
 * @param fullName The full name of the repository.  This is the owner name followed by a slash followed by the
 *                 repository name.
 * @param description A human readable description of the repository
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

    @JsonIgnore
    public GitHubRepositoryCoordinates getCoordinates() {
        return GitHubRepositoryCoordinates.fromFullName(fullName);
    }
}
