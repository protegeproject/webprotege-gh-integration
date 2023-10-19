package edu.stanford.protege.ghintegration;

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

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-10-17
 */
@JsonTest
@AutoConfigureJson
public class GetLinkedGitHubRepositoryRequestTest {

    @Autowired
    private JacksonTester<GetLinkedGitHubRepositoryRequest> tester;

    private GetLinkedGitHubRepositoryRequest request;

    private ProjectId projectId;

    @BeforeEach
    void setUp() {
        projectId = ProjectId.generate();
        request = new GetLinkedGitHubRepositoryRequest(projectId);
    }

    @Test
    void shouldHaveExpectedChannel() {
        assertThat(request.getChannel()).isEqualTo("webprotege.github.GetLinkedGitHubRepository");
    }

    @Test
    void shouldSerializeRequest() throws IOException {
        var content = tester.write(request);
        assertThat(content).hasJsonPathStringValue("projectId", projectId.value());
    }

    @Test
    void shouldDeserializeRequest() throws IOException {
       var json = """
               { "projectId" : "11111111-2222-3333-4444-555555555555" }
               """;
       var read = tester.readObject(new StringReader(json));
       assertThat(read.projectId().value()).isEqualTo("11111111-2222-3333-4444-555555555555");
    }
}
