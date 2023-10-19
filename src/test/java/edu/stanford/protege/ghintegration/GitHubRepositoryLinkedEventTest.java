package edu.stanford.protege.ghintegration;

import edu.stanford.protege.webprotege.common.EventId;
import edu.stanford.protege.webprotege.common.ProjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
@AutoConfigureJson
class GitHubRepositoryLinkedEventTest {

    private GitHubRepositoryLinkedEvent event;

    private EventId eventId;

    private ProjectId projectId;

    private GitHubRepositoryCoordinates repositoryCoordinates;

    @Autowired
    private JacksonTester<GitHubRepositoryLinkedEvent> tester;

    @BeforeEach
    void setUp() {
        eventId = EventId.generate();
        projectId = ProjectId.generate();
        repositoryCoordinates = GitHubRepositoryCoordinates.of("ACME", "R1");
        event = new GitHubRepositoryLinkedEvent(eventId, projectId, repositoryCoordinates);
    }

    @Test
    void shouldGetChannel() {
        assertThat(event.getChannel()).isEqualTo("webprotege.events.github.GitHubRepositoryLinked");
    }

    @Test
    void eventId() {
        assertThat(event.eventId()).isEqualTo(eventId);
    }

    @Test
    void projectId() {
        assertThat(event.projectId()).isEqualTo(projectId);
    }

    @Test
    void repositoryCoordinates() {
        assertThat(event.repositoryCoordinates()).isEqualTo(repositoryCoordinates);
    }

    @Test
    void shouldSerializeJson() throws IOException {
        var content = tester.write(event);
        assertThat(content).hasJsonPathStringValue("eventId", eventId.id());
        assertThat(content).hasJsonPathStringValue("projectId", projectId.value());
        assertThat(content).hasJsonPathStringValue("repositoryCoordinates.ownerName", "ACME");
        assertThat(content).hasJsonPathStringValue("repositoryCoordinates.repositoryName", "R1");
    }

    @Test
    void shouldDeserializeJson() throws IOException {
        var json = """
                 {
                    "@type" : "webprotege.events.github.GitHubRepositoryLinked",
                    "eventId" : "11111111-2222-3333-4444-555555555555",
                    "projectId" : "11111111-1111-1111-1111-111111111111",
                    "repositoryCoordinates" : {
                        "ownerName" : "ACME",
                        "repositoryName" : "R1"
                    }
                 }
                """;
        var readEvent = tester.readObject(new StringReader(json));
        assertThat(readEvent.eventId().id()).isEqualTo("11111111-2222-3333-4444-555555555555");
        assertThat(readEvent.projectId().value()).isEqualTo("11111111-1111-1111-1111-111111111111");
        assertThat(readEvent.repositoryCoordinates().ownerName()).isEqualTo("ACME");
        assertThat(readEvent.repositoryCoordinates().repositoryName()).isEqualTo("R1");
    }
}