package edu.stanford.protege.github.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.ProjectId;
import edu.stanford.protege.webprotege.common.Response;

import java.util.Objects;

@JsonTypeName("UnlinkGitHubRepositoryResponse")
public record UnlinkGitHubRepositoryResponse(@JsonProperty("projectId")
                                             ProjectId projectId) implements Response {

    public UnlinkGitHubRepositoryResponse(@JsonProperty("projectId") ProjectId projectId) {
        this.projectId = Objects.requireNonNull(projectId);
    }
}
