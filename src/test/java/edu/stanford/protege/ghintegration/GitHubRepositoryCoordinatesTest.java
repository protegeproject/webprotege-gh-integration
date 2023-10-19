package edu.stanford.protege.ghintegration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GitHubRepositoryCoordinatesTest {

    protected static final String TEST_OWNER_NAME = "TestOwnerName";

    protected static final String TEST_REPO_NAME = "TestRepoName";

    private GitHubRepositoryCoordinates coords;

    @BeforeEach
    void setUp() {
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
}