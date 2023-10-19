package edu.stanford.protege.github;

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
public class UnlinkGitHubRepositoryResponseTest {

    protected static final String PROJECT_ID = "11111111-2222-3333-4444-555555555555";

    @Autowired
    private JacksonTester<UnlinkGitHubRepositoryResponse> tester;

    @Test
    void shouldSerializeJson() throws IOException {
        var content = tester.write(new UnlinkGitHubRepositoryResponse(ProjectId.valueOf(PROJECT_ID)));
        assertThat(content).hasJsonPathStringValue("projectId", PROJECT_ID);
    }

    @Test
    void shouldDeserializeJson() throws IOException {
        var json = """
                    {
                        "projectId" : "11111111-2222-3333-4444-555555555555"
                    }
                """;
        var response = tester.readObject(new StringReader(json));
        assertThat(response.projectId().value()).isEqualTo(PROJECT_ID);
    }

    @Test
    void shouldThrowNpeIfProjectIdIsNull() {
        assertThrows(NullPointerException.class, () -> {
            new UnlinkGitHubRepositoryResponse(null);
        });
    }
}