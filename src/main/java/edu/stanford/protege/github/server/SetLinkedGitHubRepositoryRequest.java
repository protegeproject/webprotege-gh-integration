package edu.stanford.protege.github.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.ProjectRequest;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-10-17
 */
@JsonTypeName("SetLinkedGitHubRepositoryRequest")
public record SetLinkedGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                               @JsonProperty("repositoryCoordinates") GitHubRepositoryCoordinates repositoryCoordinates) implements ProjectRequest<SetLinkedGitHubRepositoryResponse> {

    public static final String CHANNEL = "webprotege.github.SetLinkedGitHubRepository";

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
