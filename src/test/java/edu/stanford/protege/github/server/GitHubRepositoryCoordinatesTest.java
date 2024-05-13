package edu.stanford.protege.github.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.stanford.protege.github.server.GitHubRepositoryCoordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GitHubRepositoryCoordinatesTest {

    protected static final String TEST_OWNER_NAME = "TestOwnerName";

    protected static final String TEST_REPO_NAME = "TestRepoName";

    private GitHubRepositoryCoordinates coords;

    private JacksonTester<GitHubRepositoryCoordinates> jacksonTester;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        coords = GitHubRepositoryCoordinates.of(TEST_OWNER_NAME, TEST_REPO_NAME);
    }

    @Test
    void shouldStoreOwnerName() {
        assertThat(coords.ownerName()).isEqualTo(TEST_OWNER_NAME);
    }

    @Test
    void shouldStoreRepoName() {
        assertThat(coords.repositoryName()).isEqualTo(TEST_REPO_NAME);
    }

    @Test
    void shouldProvideFullName() {
        assertThat(coords.getFullName()).isEqualTo(TEST_OWNER_NAME + "/" + TEST_REPO_NAME);
    }

    @Test
    void shouldConstructFromFullName() {
        var coords = GitHubRepositoryCoordinates.fromFullName("ACME/R1");
        assertThat(coords.ownerName()).isEqualTo("ACME");
        assertThat(coords.repositoryName()).isEqualTo("R1");
    }

    @Test
    void shouldThrowIaeForInvalidFullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            GitHubRepositoryCoordinates.fromFullName("Incorrect full name");
        });
    }

    @Test
    void shouldThrowNpeIfOwnerNameIsNull() {
        assertThrows(NullPointerException.class, () -> {
            GitHubRepositoryCoordinates.of(null, TEST_REPO_NAME);
        });
    }

    @Test
    void shouldThrowNpeIfRepoNameIsNull() {
        assertThrows(NullPointerException.class, () -> {
            GitHubRepositoryCoordinates.of(TEST_OWNER_NAME, null);
        });
    }

    @Test
    void shouldSerializeRepositoryCoordinates() throws IOException {
        var json = jacksonTester.write(coords);
        assertThat(json).hasJsonPathStringValue("ownerName", TEST_OWNER_NAME);
        assertThat(json).hasJsonPathStringValue("repositoryName", TEST_REPO_NAME);
    }

    @Test
    void shouldDeserializeRepositoryCoodinates() throws IOException {
        var json = """
                {
                    "ownerName" : "TestOwnerName",
                    "repositoryName" : "TestRepoName"
                }
                """;
        var read = jacksonTester.read(new StringReader(json));
        var readCoords = read.getObject();
        assertThat(readCoords.ownerName()).isEqualTo(TEST_OWNER_NAME);
        assertThat(readCoords.repositoryName()).isEqualTo(TEST_REPO_NAME);
    }
}