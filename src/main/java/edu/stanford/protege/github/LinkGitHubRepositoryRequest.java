package edu.stanford.protege.github;

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
@JsonTypeName("LinkGitHubRepositoryRequest")
public record LinkGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId,
                                          @JsonProperty("repositoryCoordinates") GitHubRepositoryCoordinates repo) implements ProjectRequest<LinkGitHubRepositoryResponse> {

    public static final String CHANNEL = "webprotege.github.LinkGitHubRepository";

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
