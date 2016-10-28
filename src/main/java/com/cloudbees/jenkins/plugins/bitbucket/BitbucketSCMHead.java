package com.cloudbees.jenkins.plugins.bitbucket;

/**
 * @author Stephen Connolly
 */
public interface BitbucketSCMHead {
    String getRepoOwner();

    String getRepoName();

    String getBranchName();
}
