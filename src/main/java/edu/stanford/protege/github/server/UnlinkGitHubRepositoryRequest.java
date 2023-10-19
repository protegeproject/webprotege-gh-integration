package edu.stanford.protege.github.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.ProjectRequest;

import javax.annotation.Nonnull;

@JsonTypeName("UnlinkGitHubRepositoryRequest")
public record UnlinkGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId) implements ProjectRequest<LinkGitHubRepositoryResponse> {

    public static final String CHANNEL = "webprotege.github.UnlinkGitHubRepository";

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
