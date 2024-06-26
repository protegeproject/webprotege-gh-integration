package edu.stanford.protege.github;

import edu.stanford.protege.github.SetLinkedGitHubRepositoryResponse;
import edu.stanford.protege.github.GitHubRepositoryCoordinates;
import edu.stanford.protege.webprotege.common.ProjectId;
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
public class SetLinkedGitHubRepositoryResponseTest {

    protected static final String PROJECT_ID = "11111111-2222-3333-4444-555555555555";

    @Autowired
    private JacksonTester<SetLinkedGitHubRepositoryResponse> tester;

    @Test
    void shouldSerializeJson() throws IOException {
        var content = tester.write(new SetLinkedGitHubRepositoryResponse(ProjectId.valueOf(PROJECT_ID),
                                                                         GitHubRepositoryCoordinates.of("ACME", "R1")));
        assertThat(content).hasJsonPathStringValue("projectId", PROJECT_ID);
        assertThat(content).hasJsonPathStringValue("repositoryCoordinates.ownerName", "ACME");
        assertThat(content).hasJsonPathStringValue("repositoryCoordinates.repositoryName", "R1");
    }

    @Test
    void shouldDeserializeJson() throws IOException {
        var json = """
                    {
                        "projectId" : "11111111-2222-3333-4444-555555555555",
                        "repositoryCoordinates" : {
                            "ownerName" : "ACME",
                            "repositoryName" : "R1"
                        }
                    }
                """;
        var response = tester.readObject(new StringReader(json));
        assertThat(response.projectId().value()).isEqualTo(PROJECT_ID);
        assertThat(response.repositoryCoordinates().ownerName()).isEqualTo("ACME");
        assertThat(response.repositoryCoordinates().repositoryName()).isEqualTo("R1");
    }

    @Test
    void shouldThrowNpeIfProjectIdIsNull() {
        assertThrows(NullPointerException.class, () -> {
           new SetLinkedGitHubRepositoryResponse(null, GitHubRepositoryCoordinates.of("ACME", "R1"));
        });
    }

    @Test
    void shouldThrowNpeIfRepositoryCoordinatesIsNull() {
        assertThrows(NullPointerException.class, () -> {
           new SetLinkedGitHubRepositoryResponse(ProjectId.generate(), null);
        });
    }
}