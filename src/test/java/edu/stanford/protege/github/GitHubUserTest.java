package edu.stanford.protege.github;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-10-19
 */
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureJson
@JsonTest
public class GitHubUserTest {

    protected static final String NODE_ID = "MDQ6VXNlcjE=";

    protected static final String LOGIN = "octocat";

    protected static final String AVATAR_URL = "https://github.com/images/error/octocat_happy.gif";

    protected static final String URL = "https://api.github.com/users/octocat";

    protected static final String HTML_URL = "https://github.com/octocat";

    @Autowired
    private JacksonTester<GitHubUser> tester;

    @Test
    void shouldParseUser() throws IOException {
        var jsonInputStream = GitHubUserTest.class.getResourceAsStream("/user.json");
        var content = tester.read(jsonInputStream);
        var user = content.getObject();

        assertThat(user.login()).isEqualTo(LOGIN);
        assertThat(user.id()).isEqualTo(1);
        assertThat(user.nodeId()).isEqualTo(NODE_ID);
        assertThat(user.avatarUrl()).isEqualTo(AVATAR_URL);
        assertThat(user.htmlUrl()).isEqualTo(HTML_URL);
        assertThat(user.type()).isEqualTo(GitHubUserType.USER);
        assertThat(user.siteAdmin()).isFalse();
    }

    @Test
    void shouldWriteUser() throws IOException {
        var user = getUser();
        var content = tester.write(user);
        assertThat(content).hasJsonPathValue("login", LOGIN);
        assertThat(content).hasJsonPathValue("id", 1);
        assertThat(content).hasJsonPathValue("node_id", NODE_ID);
        assertThat(content).hasJsonPathValue("avatar_url", AVATAR_URL);
        assertThat(content).hasJsonPathValue("url", URL);
        assertThat(content).hasJsonPathValue("html_url", HTML_URL);
        assertThat(content).hasJsonPathValue("type", "User");
        assertThat(content).hasJsonPathValue("site_admin", false);
    }

    protected static GitHubUser getUser() {
        return GitHubUser.get(LOGIN, 1, NODE_ID, AVATAR_URL, URL, HTML_URL, GitHubUserType.USER, false);
    }

    @Test
    public void testGitHubUserProperties() {
        var user = getUser();
        assertThat(user.login()).isEqualTo(LOGIN);
        assertThat(user.id()).isEqualTo(1);
        assertThat(user.nodeId()).isEqualTo(NODE_ID);
        assertThat(user.avatarUrl()).isEqualTo(AVATAR_URL);
        assertThat(user.htmlUrl()).isEqualTo(HTML_URL);
        assertThat(user.type()).isEqualTo(GitHubUserType.USER);
        assertThat(user.siteAdmin()).isFalse();
    }

    @Test
    public void testGitHubUserWithNullValues() {
        GitHubUser user = GitHubUser.get(null, 1, null, null, null, null, null, false);

        assertThat(user.login()).isEmpty();
        assertThat(user.id()).isEqualTo(1);
        assertThat(user.nodeId()).isEmpty();
        assertThat(user.avatarUrl()).isEmpty();
        assertThat(user.htmlUrl()).isEmpty();
        assertThat(user.type()).isEqualTo(GitHubUserType.USER);
        assertThat(user.siteAdmin()).isFalse();
    }

    @Test
    public void shouldGetEmptyUser() {
        var user = GitHubUser.empty();
        assertThat(user.login()).isEmpty();
        assertThat(user.id()).isEqualTo(0);
        assertThat(user.nodeId()).isEmpty();
        assertThat(user.avatarUrl()).isEmpty();
        assertThat(user.htmlUrl()).isEmpty();
        assertThat(user.type()).isEqualTo(GitHubUserType.USER);
        assertThat(user.siteAdmin()).isFalse();
    }

}
