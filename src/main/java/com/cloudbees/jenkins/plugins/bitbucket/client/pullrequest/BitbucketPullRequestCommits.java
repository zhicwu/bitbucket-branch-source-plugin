package com.cloudbees.jenkins.plugins.bitbucket.client.pullrequest;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketPullRequestCommits {
    private String next;

    private List<BitbucketPullRequestCommit> values;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<BitbucketPullRequestCommit> getValues() {
        return values;
    }

    public void setValues(List<BitbucketPullRequestCommit> values) {
        this.values = values;
    }

}
