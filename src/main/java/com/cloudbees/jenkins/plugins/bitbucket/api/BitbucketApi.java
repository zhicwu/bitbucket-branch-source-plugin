/*
 * The MIT License
 *
 * Copyright (c) 2016, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cloudbees.jenkins.plugins.bitbucket.api;

import java.util.List;

import com.cloudbees.jenkins.plugins.bitbucket.client.repository.UserRoleInRepository;

import edu.umd.cs.findbugs.annotations.CheckForNull;

/**
 * Provides access to a specific repository.
 * One API object needs to be created for each repository you want to work with.
 */
public interface BitbucketApi {

    /**
     * @return the repository owner name.
     */
    String getOwner();

    /**
     * @return the repository name.
     */
    String getRepositoryName();

    /**
     * @return the list of pull requests in the repository.
     */
    List<? extends BitbucketPullRequest> getPullRequests();

    /**
     * @param id the pull request ID
     * @return the pull request or null if the PR does not exist
     */
    @CheckForNull
    BitbucketPullRequest getPullRequestById(Integer id);

    /**
     * @return the repository specified by {@link #getOwner()}/{@link #getRepositoryName()} 
     *      (or null if repositoryName is not set)
     */
    @CheckForNull
    BitbucketRepository getRepository();

    /**
     * Post a comment to a given commit hash.
     *
     * @param hash commit hash
     * @param comment string to post as comment
     */
    void postCommitComment(String hash, String comment);

    /**
     * Checks if the given path exists in the repository at the specified branch.
     *
     * @param branch the branch name
     * @param path the path to check for
     * @return true if the path exists
     */
    boolean checkPathExists(String branch, String path);

    /**
     * @return the list of branches in the repository.
     */
    List<? extends BitbucketBranch> getBranches();

    /**
     * Resolve the commit object given its hash.
     *
     * @param hash the hash to resolve
     * @return the commit object or null if the hash does not exist
     */
    @CheckForNull
    BitbucketCommit resolveCommit(String hash);

    /**
     * Resolve the head commit hash of the pull request source repository branch.
     *
     * @param pull the pull request to resolve the source hash from
     * @return the source head hash
     */
    String resolveSourceFullHash(BitbucketPullRequest pull);

    /**
     * Register a webhook on the repository.
     */
    void registerCommitWebHook();

    /**
     * Remove the webhook (ID field required) from the repository.
     *
     * @param hook the webhook object
     */
    void removeCommitWebHook(BitbucketWebHook hook);

    /**
     * @return the list of webhooks registered in the repository.
     */
    List<? extends BitbucketWebHook> getWebHooks();

    /**
     * @return the team profile of the current owner, or null if {@link #getOwner()} is not a team ID.
     */
    @CheckForNull
    BitbucketTeam getTeam();

    /**
     * Returns the repositories where the user has the given role.
     * 
     * @param role Filter repositories by the owner having this role in. 
     *             See {@link UserRoleInRepository} for more information. 
     *             Use role = null if the repoOwner is a team ID.
     * @return the repositories list (it can be empty)
     */
    List<? extends BitbucketRepository> getRepositories(UserRoleInRepository role);

    /**
     * Returns all the repositories for the current owner (even if it's a regular user or a team).
     *
     * @return all repositories for the current {@link #getOwner()}
     */
    List<? extends BitbucketRepository> getRepositories();

    /**
     * Set the build status for the given commit hash.
     *
     * @param status the status object to be serialized
     */
    void postBuildStatus(BitbucketBuildStatus status);

    /**
     * @return true if the repository ({@link #getOwner()}/{@link #getRepositoryName()}) is private, false otherwise
     *          (if it's public or does not exists).
     */
    boolean isPrivate();

}