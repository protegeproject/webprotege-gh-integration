package edu.stanford.protege.github.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.ProjectRequest;

import javax.annotation.Nonnull;
import java.util.Objects;

@JsonTypeName("ClearLinkedGitHubRepositoryRequest")
public record ClearLinkedGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId) implements ProjectRequest<ClearLinkedGitHubRepositoryResponse> {

    public static final String CHANNEL = "webprotege.github.ClearLinkedGitHubRepository";

    @Override
    public String getChannel() {
        return CHANNEL;
    }

    public ClearLinkedGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId) {
        this.projectId = Objects.requireNonNull(projectId);
    }
}
