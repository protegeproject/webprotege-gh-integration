package edu.stanford.protege.github.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.Response;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

@JsonTypeName("GetLinkedGitHubRepositoryResponse")
public record GetLinkedGitHubRepositoryResponse(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                                @JsonProperty("repositoryCoordinates") @Nullable GitHubRepositoryCoordinates repositoryCoordinates) implements Response {

    public GetLinkedGitHubRepositoryResponse(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                             @JsonProperty("repositoryCoordinates") @Nullable GitHubRepositoryCoordinates repositoryCoordinates) {
        this.projectId = Objects.requireNonNull(projectId);
        this.repositoryCoordinates = repositoryCoordinates;
    }

    @JsonIgnore
    public Optional<GitHubRepositoryCoordinates> getRepositoryCoordinates() {
        return Optional.ofNullable(repositoryCoordinates);
    }
}
