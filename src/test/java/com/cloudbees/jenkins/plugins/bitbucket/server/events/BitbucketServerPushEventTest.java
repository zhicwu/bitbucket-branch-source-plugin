package com.cloudbees.jenkins.plugins.bitbucket.server.events;

import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketPushEvent;
import com.cloudbees.jenkins.plugins.bitbucket.client.BitbucketCloudWebhookPayload;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.BitbucketServerWebhookPayload;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Stephen Connolly
 */
public class BitbucketServerPushEventTest {
    @Test
    public void updatePayload() throws Exception {
        BitbucketPushEvent event =
                BitbucketServerWebhookPayload.pushEventFromPayload("{\"actor\":{\"username\":\"admin\","
                        + "\"displayName\":\"Administrator\"},\"repository\":{\"scmId\":\"git\","
                        + "\"project\":{\"key\":\"PROJECT_1\",\"name\":\"Project 1\"},\"slug\":\"rep_1\","
                        + "\"links\":{\"self\":[{\"href\":\"http://local.example"
                        + ".com:7990/bitbucket/projects/PROJECT_1/repos/rep_1/browse\"}]},\"public\":false,"
                        + "\"owner\":{\"username\":\"PROJECT_1\",\"displayName\":\"PROJECT_1\"},"
                        + "\"fullName\":\"PROJECT_1/rep_1\",\"ownerName\":\"PROJECT_1\"},"
                        + "\"push\":{\"changes\":[{\"created\":false,\"closed\":false,\"new\":{\"type\":\"branch\","
                        + "\"name\":\"master\",\"target\":{\"type\":\"commit\","
                        + "\"hash\":\"836d9f1f2fa7fea7831ceddcd85f0fbf1b9f8f60\"}},\"old\":{\"type\":\"branch\","
                        + "\"name\":\"master\",\"target\":{\"type\":\"commit\","
                        + "\"hash\":\"c985ccefce4df3b0ddc45a885c4881bfd8d24a47\"}}}]}}");
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
        assertThat(event.getChanges(), not(containsInAnyOrder()));
    }
}
