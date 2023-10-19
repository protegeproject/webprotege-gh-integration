package edu.stanford.protege.ghintegration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.ProjectRequest;

import javax.annotation.Nonnull;

import static edu.stanford.protege.ghintegration.GetLinkedGitHubRepositoryRequest.CHANNEL;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-10-17
 */
@JsonTypeName("GetLinkedGitHubRepositoryRequest")
public record GetLinkedGitHubRepositoryRequest(@JsonProperty("projectId") @Nonnull ProjectId projectId) implements ProjectRequest<GetLinkedGitHubRepositoryResponse> {


    public static final String CHANNEL = "webprotege.github.GetLinkedGitHubRepository";

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
