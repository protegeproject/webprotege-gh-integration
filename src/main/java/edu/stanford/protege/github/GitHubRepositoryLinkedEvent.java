package edu.stanford.protege.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.EventId;
import edu.stanford.protege.webprotege.common.ProjectEvent;
import edu.stanford.protege.webprotege.common.ProjectId;

import javax.annotation.Nonnull;

import static edu.stanford.protege.github.GitHubRepositoryLinkedEvent.CHANNEL;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-10-17
 */
@JsonTypeName(CHANNEL)
public record GitHubRepositoryLinkedEvent(@JsonProperty("eventId") @Nonnull EventId eventId,
                                          @JsonProperty("projectId") @Nonnull ProjectId projectId,
                                          @JsonProperty("repositoryCoordinates") @Nonnull GitHubRepositoryCoordinates repositoryCoordinates) implements ProjectEvent {

    public static final String CHANNEL = "webprotege.events.github.GitHubRepositoryLinked";

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
