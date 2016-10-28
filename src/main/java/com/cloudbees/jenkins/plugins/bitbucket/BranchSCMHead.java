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
package com.cloudbees.jenkins.plugins.bitbucket;

import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketPullRequest;
import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketPullRequestSource;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.io.ObjectStreamException;
import java.net.URL;
import jenkins.scm.api.SCMHead;

/**
 * {@link SCMHead} for a BitBucket branch.
 * @since FIXME
 */
public class BranchSCMHead extends SCMHead implements BitbucketSCMHead {

    private static final long serialVersionUID = 1L;

    private final String repoOwner;

    private final String repoName;

    public BranchSCMHead(String repoOwner, String repoName, String branchName) {
        super(branchName);
        this.repoOwner = repoOwner;
        this.repoName = repoName;
    }

    @Override
    public String getRepoOwner() {
        return repoOwner;
    }

    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public String getBranchName() {
        return getName();
    }

}
