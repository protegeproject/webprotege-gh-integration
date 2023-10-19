package edu.stanford.protege.github;

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
public class LinkGitHubRepositoryRequestTest {

    protected static final String ORGANIZATION_NAME = "protegeproject";

    protected static final String REPO_NAME = "testrepo";

    @Autowired
    private JacksonTester<LinkGitHubRepositoryRequest> tester;

    private LinkGitHubRepositoryRequest request;

    private ProjectId projectId;

    private GitHubRepositoryCoordinates repo;

    @BeforeEach
    void setUp() {
        projectId = ProjectId.generate();
        repo = GitHubRepositoryCoordinates.of(ORGANIZATION_NAME, REPO_NAME);
        request = new LinkGitHubRepositoryRequest(projectId, repo);
    }

    @Test
    void shouldHaveExpectedChannel() {
        assertThat(request.getChannel()).isEqualTo("webprotege.github.LinkGitHubRepository");
    }

    @Test
    void shouldSerializeRequest() throws IOException {
        var content = tester.write(request);
        assertThat(content).hasJsonPathStringValue("projectId", projectId.value());
        assertThat(content).hasJsonPathStringValue("repositoryCoordinates.ownerName", ORGANIZATION_NAME);
        assertThat(content).hasJsonPathStringValue("repositoryCoordinates.repositoryName", REPO_NAME);
    }

    @Test
    void shouldDeserializeRequest() throws IOException {
        var json = """
               { "projectId" : "11111111-2222-3333-4444-555555555555",
                 "repositoryCoordinates" : {
                     "ownerName" : "protegeproject",
                     "repositoryName" : "testrepo"
                 }
               }
               """;
        var read = tester.readObject(new StringReader(json));
        assertThat(read.projectId().value()).isEqualTo("11111111-2222-3333-4444-555555555555");
        assertThat(read.repo().ownerName()).isEqualTo("protegeproject");
        assertThat(read.repo().repositoryName()).isEqualTo("testrepo");
    }
}
