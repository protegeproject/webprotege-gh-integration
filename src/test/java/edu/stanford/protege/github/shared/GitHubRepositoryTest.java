package edu.stanford.protege.github.shared;

import static org.assertj.core.api.Assertions.assertThat;

import edu.stanford.protege.github.shared.GitHubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.io.StringReader;

@JsonTest
@AutoConfigureJson
class GitHubRepositoryTest {

    protected static final long ID = 1L;

    protected static final String NODE_ID = "node123";

    protected static final String REPO_NAME = "RepoName";

    protected static final String FULL_NAME = "OwnerName/RepoName";

    protected static final String DESCRIPTION = "Repository description";

    private GitHubRepository repository;

    @Autowired
    private JacksonTester<GitHubRepository> tester;

    @BeforeEach
    void setUp() {
        repository = new GitHubRepository(ID, NODE_ID, REPO_NAME, FULL_NAME, DESCRIPTION);
    }

    @Test
    void shouldGetId() {
        assertThat(repository.id()).isEqualTo(ID);
    }

    @Test
    void shouldGetNodeId() {
        assertThat(repository.nodeId()).isEqualTo(NODE_ID);
    }

    @Test
    void shouldGetRepoName() {
        assertThat(repository.name()).isEqualTo(REPO_NAME);
    }

    @Test
    public void shouldGetCoordinates() {
        var coordinates = repository.getCoordinates();
        assertThat(coordinates).isNotNull();
        assertThat(coordinates.getFullName()).isEqualTo(FULL_NAME);
        assertThat(coordinates.ownerName()).isEqualTo("OwnerName");
        assertThat(coordinates.repositoryName()).isEqualTo(REPO_NAME);
    }

    @Test
    void shouldDeserializeJson() throws IOException {
        var json = """
                {
                    "id" : 1,
                    "node_id": "node123",
                    "name" : "RepoName",
                    "full_name" : "OwnerName/RepoName",
                    "description" : "Repository description"
                }
                """;
        var read = tester.readObject(new StringReader(json));
        assertThat(read.id()).isEqualTo(ID);
        assertThat(read.name()).isEqualTo(REPO_NAME);
        assertThat(read.fullName()).isEqualTo(FULL_NAME);
        assertThat(read.nodeId()).isEqualTo(NODE_ID);
        assertThat(read.description()).isEqualTo(DESCRIPTION);
    }

    @Test
    void shouldDeserializeJsonWithNullFields() throws IOException {
        var json = """
                {
                    "id" : 1
                }
                """;
        var read = tester.readObject(new StringReader(json));
        assertThat(read.id()).isEqualTo(ID);
        assertThat(read.name()).isEqualTo("");
        assertThat(read.fullName()).isEqualTo("");
        assertThat(read.nodeId()).isEqualTo("");
        assertThat(read.description()).isEqualTo("");
    }

    @Test
    void shouldConstructWithNullFields() {
        var repo = new GitHubRepository(1L, null, null, null, null);
        assertThat(repo.id()).isEqualTo(ID);
        assertThat(repo.name()).isEqualTo("");
        assertThat(repo.fullName()).isEqualTo("");
        assertThat(repo.nodeId()).isEqualTo("");
        assertThat(repo.description()).isEqualTo("");
    }
}