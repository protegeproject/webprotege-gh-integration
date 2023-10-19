package edu.stanford.protege.ghintegration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.Response;

import javax.annotation.Nonnull;
import java.util.Objects;

@JsonTypeName(LinkGitHubRepositoryRequest.CHANNEL)
public record LinkGitHubRepositoryResponse(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                           @JsonProperty("repositoryCoordinates") GitHubRepositoryCoordinates repositoryCoordinates) implements Response {

    public LinkGitHubRepositoryResponse(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                        @JsonProperty("repositoryCoordinates") GitHubRepositoryCoordinates repositoryCoordinates) {
        this.projectId = Objects.requireNonNull(projectId);
        this.repositoryCoordinates = Objects.requireNonNull(repositoryCoordinates);
    }
}
