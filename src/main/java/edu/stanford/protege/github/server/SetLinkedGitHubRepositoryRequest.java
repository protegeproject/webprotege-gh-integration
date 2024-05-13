package edu.stanford.protege.github.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.ProjectRequest;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-10-17
 */
@JsonTypeName("SetLinkedGitHubRepositoryRequest")
public record SetLinkedGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                               @JsonProperty("repositoryCoordinates") @Nonnull GitHubRepositoryCoordinates repositoryCoordinates) implements ProjectRequest<SetLinkedGitHubRepositoryResponse> {

    public static final String CHANNEL = "webprotege.github.SetLinkedGitHubRepository";

    public SetLinkedGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                            @JsonProperty("repositoryCoordinates") @Nonnull GitHubRepositoryCoordinates repositoryCoordinates) {
        this.projectId = Objects.requireNonNull(projectId);
        this.repositoryCoordinates = Objects.requireNonNull(repositoryCoordinates);
    }

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
