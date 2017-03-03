package com.cloudbees.jenkins.plugins.bitbucket.server.events;

import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketPullRequestEvent;
import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketPushEvent;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.BitbucketServerWebhookPayload;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.pullrequest.BitbucketServerPullRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BitbucketServerPullRequestEventTest {
    @Test
    public void updatePayload() throws Exception {
        BitbucketPullRequestEvent event =
                BitbucketServerWebhookPayload.pullRequestEventFromPayload("{\"actor\":{\"username\":\"user\","
                        + "\"displayName\":\"User\"},\"pullrequest\":{\"id\":\"1\",\"title\":\"Markdown formatting\","
                        + "\"link\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/pull-requests/1\","
                        + "\"authorLogin\":\"User\",\"fromRef\":{\"repository\":{\"scmId\":\"git\","
                        + "\"project\":{\"key\":\"~USER\",\"name\":\"User\"},\"slug\":\"rep_1\","
                        + "\"links\":{\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/~USER/repos/rep_1/browse\"}]},\"public\":false,"
                        + "\"owner\":{\"username\":\"~USER\",\"displayName\":\"~USER\"},\"fullName\":\"~USER/rep_1\","
                        + "\"ownerName\":\"~USER\"},\"commit\":{\"message\":null,\"date\":null,"
                        + "\"hash\":\"feb8d676cd70406cecd4128c8fd1bee30282db11\",\"authorTimestamp\":0},"
                        + "\"branch\":{\"name\":\"master\","
                        + "\"rawNode\":\"feb8d676cd70406cecd4128c8fd1bee30282db11\"}},"
                        + "\"toRef\":{\"repository\":{\"scmId\":\"git\",\"project\":{\"key\":\"PROJECT_1\","
                        + "\"name\":\"Project 1\"},\"slug\":\"rep_1\",\"links\":{\"self\":[{\"href\":\"http://local"
                        + ".example.com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse\"}]},\"public\":false,"
                        + "\"owner\":{\"username\":\"PROJECT_1\",\"displayName\":\"PROJECT_1\"},"
                        + "\"fullName\":\"PROJECT_1/rep_1\",\"ownerName\":\"PROJECT_1\"},"
                        + "\"commit\":{\"message\":null,\"date\":null,"
                        + "\"hash\":\"d235f0c0aa22f4c75b2fb63b217e39e2d3c29f49\",\"authorTimestamp\":0},"
                        + "\"branch\":{\"name\":\"master\","
                        + "\"rawNode\":\"d235f0c0aa22f4c75b2fb63b217e39e2d3c29f49\"}}},"
                        + "\"repository\":{\"scmId\":\"git\",\"project\":{\"key\":\"PROJECT_1\",\"name\":\"Project "
                        + "1\"},\"slug\":\"rep_1\",\"links\":{\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse\"}]},\"public\":false,"
                        + "\"owner\":{\"username\":\"PROJECT_1\",\"displayName\":\"PROJECT_1\"},"
                        + "\"fullName\":\"PROJECT_1/rep_1\",\"ownerName\":\"PROJECT_1\"}}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("PROJECT_1/rep_1"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("Project 1"));
        assertThat(event.getRepository().getOwner().getUsername(), is("PROJECT_1"));
        assertThat(event.getRepository().getRepositoryName(), is("rep_1"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("http://local.example.com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("Markdown formatting"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("User"));
        assertThat(event.getPullRequest().getLink(),
                is("http://local.example.com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/pull-requests/1"));
        
        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("PROJECT_1/rep_1"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(), is("Project 1"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("PROJECT_1"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("rep_1"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("http://local.example.com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(), is("d235f0c0aa22f4c75b2fb63b217e39e2d3c29f49"));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(), is(
                "d235f0c0aa22f4c75b2fb63b217e39e2d3c29f49"));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("~USER/rep_1"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(), is("User"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("~USER"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("rep_1"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                anyOf(is("http://local.example.com:7990/bitbucket/projects/~USER/repos/rep_1/browse"),
                        is("http://local.example.com:7990/bitbucket/users/user/repos/rep_1/browse")));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                is("feb8d676cd70406cecd4128c8fd1bee30282db11"));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(), is(
                "feb8d676cd70406cecd4128c8fd1bee30282db11"));
    }

    @Test
    public void apiResponse() throws Exception {
        BitbucketServerPullRequest pullRequest =
                new ObjectMapper().readValue("\n"
                        + "{\"id\":1,\"version\":1,\"title\":\"Markdown formatting\",\"description\":\"my pr\","
                        + "\"state\":\"OPEN\",\"open\":true,\"closed\":false,\"createdDate\":1488549656836,"
                        + "\"updatedDate\":1488550788045,\"fromRef\":{\"id\":\"refs/heads/master\","
                        + "\"displayId\":\"master\",\"latestCommit\":\"feb8d676cd70406cecd4128c8fd1bee30282db11\","
                        + "\"repository\":{\"slug\":\"rep_1\",\"id\":12,\"name\":\"rep_1\",\"scmId\":\"git\","
                        + "\"state\":\"AVAILABLE\",\"statusMessage\":\"Available\",\"forkable\":true,"
                        + "\"origin\":{\"slug\":\"rep_1\",\"id\":1,\"name\":\"rep_1\",\"scmId\":\"git\","
                        + "\"state\":\"AVAILABLE\",\"statusMessage\":\"Available\",\"forkable\":true,"
                        + "\"project\":{\"key\":\"PROJECT_1\",\"id\":1,\"name\":\"Project 1\","
                        + "\"description\":\"Default configuration project #1\",\"public\":false,\"type\":\"NORMAL\","
                        + "\"links\":{\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1\"}]}},\"public\":false,"
                        + "\"links\":{\"clone\":[{\"href\":\"http://admin@local.example"
                        + ".com:7990/bitbucket/scm/project_1/rep_1.git\",\"name\":\"http\"},"
                        + "{\"href\":\"ssh://git@local.example.com:7999/project_1/rep_1.git\",\"name\":\"ssh\"}],"
                        + "\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse\"}]}},"
                        + "\"project\":{\"key\":\"~USER\",\"id\":22,\"name\":\"User\",\"type\":\"PERSONAL\","
                        + "\"owner\":{\"name\":\"user\",\"emailAddress\":\"user@example.com\",\"id\":2,"
                        + "\"displayName\":\"User\",\"active\":true,\"slug\":\"user\",\"type\":\"NORMAL\","
                        + "\"links\":{\"self\":[{\"href\":\"http://local.example.com:7990/bitbucket/users/user\"}]}},"
                        + "\"links\":{\"self\":[{\"href\":\"http://local.example.com:7990/bitbucket/users/user\"}]}},"
                        + "\"public\":false,\"links\":{\"clone\":[{\"href\":\"ssh://git@local.example"
                        + ".com:7999/~user/rep_1.git\",\"name\":\"ssh\"},{\"href\":\"http://admin@local.example"
                        + ".com:7990/bitbucket/scm/~user/rep_1.git\",\"name\":\"http\"}],"
                        + "\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/users/user/repos/rep_1/browse\"}]}}},"
                        + "\"toRef\":{\"id\":\"refs/heads/master\",\"displayId\":\"master\","
                        + "\"latestCommit\":\"d235f0c0aa22f4c75b2fb63b217e39e2d3c29f49\","
                        + "\"repository\":{\"slug\":\"rep_1\",\"id\":1,\"name\":\"rep_1\",\"scmId\":\"git\","
                        + "\"state\":\"AVAILABLE\",\"statusMessage\":\"Available\",\"forkable\":true,"
                        + "\"project\":{\"key\":\"PROJECT_1\",\"id\":1,\"name\":\"Project 1\","
                        + "\"description\":\"Default configuration project #1\",\"public\":false,\"type\":\"NORMAL\","
                        + "\"links\":{\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1\"}]}},\"public\":false,"
                        + "\"links\":{\"clone\":[{\"href\":\"http://admin@local.example"
                        + ".com:7990/bitbucket/scm/project_1/rep_1.git\",\"name\":\"http\"},"
                        + "{\"href\":\"ssh://git@local.example.com:7999/project_1/rep_1.git\",\"name\":\"ssh\"}],"
                        + "\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse\"}]}}},\"locked\":false,"
                        + "\"author\":{\"user\":{\"name\":\"user\",\"emailAddress\":\"user@example.com\",\"id\":2,"
                        + "\"displayName\":\"User\",\"active\":true,\"slug\":\"user\",\"type\":\"NORMAL\","
                        + "\"links\":{\"self\":[{\"href\":\"http://local.example.com:7990/bitbucket/users/user\"}]}},"
                        + "\"role\":\"AUTHOR\",\"approved\":false,\"status\":\"UNAPPROVED\"},\"reviewers\":[],"
                        + "\"participants\":[],\"links\":{\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/pull-requests/1\"}]}}", BitbucketServerPullRequest.class);
        assertThat(pullRequest, notNullValue());
        assertThat(pullRequest.getTitle(), is("Markdown formatting"));
        assertThat(pullRequest.getAuthorLogin(), is("User"));
        assertThat(pullRequest.getLink(),
                is("http://local.example.com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/pull-requests/1"));

        assertThat(pullRequest.getDestination(), notNullValue());
        assertThat(pullRequest.getDestination().getRepository(), notNullValue());
        assertThat(pullRequest.getDestination().getRepository().getScm(), is("git"));
        assertThat(pullRequest.getDestination().getRepository().getFullName(), is("PROJECT_1/rep_1"));
        assertThat(pullRequest.getDestination().getRepository().getOwner().getDisplayName(),
                is("Project 1"));
        assertThat(pullRequest.getDestination().getRepository().getOwner().getUsername(), is("PROJECT_1"));
        assertThat(pullRequest.getDestination().getRepository().getRepositoryName(), is("rep_1"));
        assertThat(pullRequest.getDestination().getRepository().isPrivate(), is(true));
        assertThat(pullRequest.getDestination().getRepository().getLinks(), notNullValue());
        assertThat(pullRequest.getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(pullRequest.getDestination().getRepository().getLinks().get("self").getHref(),
                is("http://local.example.com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse"));
        assertThat(pullRequest.getDestination().getBranch(), notNullValue());
        assertThat(pullRequest.getDestination().getBranch().getName(), is("master"));
        assertThat(pullRequest.getDestination().getBranch().getRawNode(),
                is("d235f0c0aa22f4c75b2fb63b217e39e2d3c29f49"));
        assertThat(pullRequest.getDestination().getCommit(), notNullValue());
        assertThat(pullRequest.getDestination().getCommit().getHash(), is(
                "d235f0c0aa22f4c75b2fb63b217e39e2d3c29f49"));

        assertThat(pullRequest.getSource(), notNullValue());
        assertThat(pullRequest.getSource().getRepository(), notNullValue());
        assertThat(pullRequest.getSource().getRepository().getScm(), is("git"));
        assertThat(pullRequest.getSource().getRepository().getFullName(), is("~USER/rep_1"));
        assertThat(pullRequest.getSource().getRepository().getOwner().getDisplayName(), is("User"));
        assertThat(pullRequest.getSource().getRepository().getOwner().getUsername(), is("~USER"));
        assertThat(pullRequest.getSource().getRepository().getRepositoryName(), is("rep_1"));
        assertThat(pullRequest.getSource().getRepository().isPrivate(), is(true));
        assertThat(pullRequest.getSource().getRepository().getLinks(), notNullValue());
        assertThat(pullRequest.getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(pullRequest.getSource().getRepository().getLinks().get("self").getHref(),
                anyOf(is("http://local.example.com:7990/bitbucket/projects/~USER/repos/rep_1/browse"),
                        is("http://local.example.com:7990/bitbucket/users/user/repos/rep_1/browse"))
        );

        assertThat(pullRequest.getSource().getBranch(), notNullValue());
        assertThat(pullRequest.getSource().getBranch().getName(), is("master"));
        assertThat(pullRequest.getSource().getBranch().getRawNode(),
                is("feb8d676cd70406cecd4128c8fd1bee30282db11"));
        assertThat(pullRequest.getSource().getCommit(), notNullValue());
        assertThat(pullRequest.getSource().getCommit().getHash(), is(
                "feb8d676cd70406cecd4128c8fd1bee30282db11"));

    }
}
