package edu.stanford.protege.github.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.Response;

import java.util.Objects;

import static edu.stanford.protege.github.server.ClearLinkedGitHubRepositoryRequest.CHANNEL;

@JsonTypeName(CHANNEL)
public record ClearLinkedGitHubRepositoryResponse(@JsonProperty("projectId")
                                             ProjectId projectId) implements Response {

    public ClearLinkedGitHubRepositoryResponse(@JsonProperty("projectId") ProjectId projectId) {
        this.projectId = Objects.requireNonNull(projectId);
    }
}
